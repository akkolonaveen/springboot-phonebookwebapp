package com.java.phonebook.exception;
import java.lang.Exception;

public class NoDataFoundException  extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174433283974092396L;
	
	public NoDataFoundException()
	{
		
	}
	public NoDataFoundException(String msg)
	{
		super(msg);
	}
 
}
