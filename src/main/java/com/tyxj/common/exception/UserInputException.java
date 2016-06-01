package com.tyxj.common.exception;

/**
 * @ClassName: UserInputException
 * @Description: 自定义异常
 * @author WangX
 * @Date: 2016年5月12日 下午5:22:20
 *
 */
public class UserInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132495062317028028L;

	
	public UserInputException() {
	}

	public UserInputException(String message){
		super(message);
	}
	
}
