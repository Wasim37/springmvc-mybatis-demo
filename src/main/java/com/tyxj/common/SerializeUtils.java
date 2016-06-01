package com.tyxj.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtils.class);

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {

		Object result = null;

		if (isEmpty(bytes)) {
			return null;
		}
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
			result = objectInputStream.readObject();
		} catch (Exception e) {
			LOGGER.error("Failed to deserialize", e);
		}
		return result;
	}

	public static boolean isEmpty(byte[] data) {
		return data == null || data.length == 0;
	}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {

		byte[] result = null;

		if (object == null) {
			return new byte[0];
		}
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
			if (!(object instanceof Serializable)) {
				throw new IllegalArgumentException(SerializeUtils.class.getSimpleName()
								+ " requires a Serializable payload "
								+ "but received an object of type ["
								+ object.getClass().getName() + "]");
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			result = byteStream.toByteArray();

		} catch (Exception ex) {
			LOGGER.error("Failed to serialize", ex);
		}
		return result;
	}
}
