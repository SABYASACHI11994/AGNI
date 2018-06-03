package com.agni.demo.util;

public class LoginException extends Exception{

	private String message = null;

	public LoginException(String message, Throwable cause)
	{
		super(message);
		this.message = message;
		super.setStackTrace(cause.getStackTrace());
	}

	public LoginException(String message)
	{
		super(message);
		this.message = message;
	}

	public String toString()
	{
		return this.message;
	}

	public String getMessage()
	{
		return this.message;
	}
}
