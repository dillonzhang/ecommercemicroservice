package com.bdtv.ms.ecom.cart.service.service.exception;

/**
 * @author dangao on 5/11/2018.
 * @version 1.0
 */
public class ServiceUnavailableException extends RuntimeException
{
	public ServiceUnavailableException(final String message)
	{
		super(message);
	}
}
