package com.tyxj.common.exception;

/**
 * 
 * @ClassName: UserNotLoginException
 * @Description: 自定义用户登录异常
 * @author WangX
 * @Date: 2016年5月12日 下午5:21:27
 *
 */
public class UserNotLoginException extends UserInputException{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7132495062317028028L;
	
	public UserNotLoginException(){
		super("请先登录再进行操作！");
	}
	public UserNotLoginException(String message){
		super(message);
	}
}
