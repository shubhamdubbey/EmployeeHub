package com.cts.hr.utility;

@SuppressWarnings("serial")
public class UnauthorisedException extends Exception{
	public UnauthorisedException(String s)
    {
        super(s);
    }
}
