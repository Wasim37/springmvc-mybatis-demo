package com.tyxj.beanstalk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import com.tyxj.common.MsgTimer;
import com.tyxj.common.PropertyFactory;

import common.Logger;

/**
 * 
 * Wraps the beanstalk connection.  
 * 
 * 
 * @author dustin
 *
 */
public class BeanstalkConnection {

	private static final Logger LOGGER = Logger.getLogger(BeanstalkConnection.class);
	private static MsgTimer msgTimer = null;
	private SocketChannel channel;
	private ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
	
	public void connect(String addr, int port) throws BeanstalkException {
		try {
			this.channel = SocketChannel.open();
			this.channel.connect(new InetSocketAddress(addr, port));
			this.channel.finishConnect();
			if(msgTimer!=null){
				msgTimer.end();
				msgTimer = null;
			}
		} catch (Exception x) {
			long delay = Long.parseLong(PropertyFactory.getMonitorDelay());
			long period = Long.parseLong(PropertyFactory.getMonitorPeriod());
			if(msgTimer==null){				
				msgTimer = new MsgTimer(delay,period);				
			}
			msgTimer.start(PropertyFactory.getMonitorTel(),"beanstalkd 异常信息："+x,"beanstalkd 队列 异常");//队列连接异常时发送短信
			LOGGER.error(x.getMessage(), x);
		}
	}
	
	public void close() {	
		try {
			outbuf.close();
		} catch (Exception x) {
			LOGGER.debug("Caught", x);
		}
		
		try {
			channel.close();
		} catch (Exception x) {
			LOGGER.debug("Caught", x);
		}
	}

	public boolean isOpen() {
		return channel != null && channel.isOpen();
	}
	
	public void write(String str) throws BeanstalkDisconnectedException, BeanstalkException{
		try {
			ByteBuffer buf = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
			while(buf.hasRemaining()) {
				channel.write(buf);
			}
		} catch (Exception x) {
			this.throwException(x.getMessage(),x);
		}
	}
	
	private void throwException(String message,Exception x) throws BeanstalkDisconnectedException, BeanstalkException{
		if (x instanceof NotYetConnectedException) {
			throw new BeanstalkDisconnectedException(message,x);
		}
		if (x instanceof IOException) {
			throw new BeanstalkDisconnectedException(message,x);
		}

		
		throw new BeanstalkException(message,x);
	}
	
	public void write(byte[] bytes) throws BeanstalkDisconnectedException, BeanstalkException{
		try {
			ByteBuffer buf = ByteBuffer.wrap(bytes);
			while(buf.hasRemaining()) {
				channel.write(buf);
			}
		} catch (Exception x) {
			this.throwException(x.getMessage(),x);
		}
	}
	
	/**
	 * returns the control response.  ends with \r\n
	 * @return
	 */
	public String readControlResponse() throws BeanstalkDisconnectedException, BeanstalkException{
		//clear the old out buffer
		String response = null;
		int count = 0;
		while (response == null) {
			count++;

			outbuf = new ByteArrayOutputStream();
			ByteBuffer buf = ByteBuffer.allocate(4096);
			if (count > 10000) {
				throw new BeanstalkException(
						"OH Snap, nothing to read from the buffer for 100 seconds!");
			}

			try {
				if (channel.read(buf) > 0) {
					byte[] bytes = buf.array();

					ByteArrayOutputStream stringBuf = new ByteArrayOutputStream();
					byte lastByte = ' ';

					for (int i = 0; i < buf.position(); i++) {
						byte curByte = bytes[i];
						if (lastByte == '\r' && curByte == '\n' && response == null) {
							response = new String(stringBuf.toByteArray(),StandardCharsets.UTF_8).trim();
							if (response.isEmpty()) {
								LOGGER.warn("Errant line end found, possibly from the previous request. skipping");
								response = null;
							}
							continue;
						}

						if (response == null) {
							stringBuf.write(curByte);
						} else {
							outbuf.write(curByte);
						}
						lastByte = curByte;
					}
				}else{
					LOGGER.warn("Nothing in the buffer, sleeping for 100 millis, will try again");
					Thread.sleep(100);
				}
			} catch (Exception x) {
				this.throwException(x.getMessage(),x);
			}
		}
		return response;
	}
	
	public byte[] readBytes(int numBytes) throws BeanstalkDisconnectedException, BeanstalkException{
		
		byte[] bytes = new byte[numBytes];

		byte[] array = this.outbuf.toByteArray();
		this.outbuf = new ByteArrayOutputStream();
		
		int bytesWritten = 0;
		//first read any bytes that are already in the outbuffer.
		for (int i=0; i < array.length; i++) {
			if (bytesWritten < bytes.length) {
				bytes[i] = array[i];
				bytesWritten++;
			} else {
				this.outbuf.write(array[i]);
			}
			
		}
		if (bytesWritten >= bytes.length) {
			return bytes;
		}
		
		int numRead = 1;
		while(numRead >0) {
			//then read the bytes waiting in the channel.
			ByteBuffer buf = ByteBuffer.allocate(4096);
			try {
				numRead = channel.read(buf);
			} catch (Exception x) {
				this.throwException(x.getMessage(),x);
			}
			
			byte[] read = buf.array();
			for (int i=0 ; i<numRead; i++) {
				if (bytesWritten < bytes.length) {
					bytes[bytesWritten] = read[i];
				} else {
					this.outbuf.write(read[i]);
				}
				bytesWritten++;
			}
			if (bytesWritten >= bytes.length) {
				LOGGER.debug("468 GOT : " + bytesWritten + " " + bytes.length);
				return bytes;
			}
		}
		return bytes;
	}
		
}
