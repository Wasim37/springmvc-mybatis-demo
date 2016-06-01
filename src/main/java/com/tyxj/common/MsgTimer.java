package com.tyxj.common;

import java.util.Timer;
import java.util.TimerTask;

import com.tyxj.common.MessageUtil;
import com.tyxj.common.PropertyFactory;
import com.tyxj.common.MsgTimer.MsgTask;

/**
 * @description 
 * @author zhr
 * @project video
 * @create_date 2014-7-17 下午12:10:01
 */
public class MsgTimer {
	private static volatile Timer timer = null;
	private static Object oLock = new Object();
	private long delay;
	private long period;
	private static int maxmsg = 0;
	public MsgTimer(long delay, long period){
		this.delay = delay;
		this.period = period;
	}
	public void start(String telephone,String info,String infoTitle){
		if(timer==null){
			synchronized (MsgTimer.oLock) {
				if(timer == null){
				timer = new Timer();  
		        timer.schedule(new MsgTask(telephone,info,infoTitle), delay, period);
				}
			}
		}
	}
	public void end(){
		if(timer!=null){
			timer.cancel();
			timer = null;
		}
	}
	static class MsgTask extends TimerTask {
		private String telephone;
		private String info;
		private String infoTitle;
		public MsgTask(String telephone,String info,String infoTitle){
			this.telephone = telephone;
			this.info = info;
			this.infoTitle = infoTitle;
		}
	    @Override  
	    public void run() {
	    	maxmsg += 1;
	    	if(maxmsg<Integer.parseInt(PropertyFactory.getMonitorMaxMsg())){
	    		MessageUtil.sendMessage(telephone,info,infoTitle);
	    	}else{
	    		maxmsg = 0;
	    	}
	    }  
	}  
}