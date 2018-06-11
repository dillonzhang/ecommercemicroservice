package com.bdtv.ms.ecom.cart.service.service.exception;

/**
 * @author dangao on 5/28/2018.
 * @version 1.0
 */
public class OutOfStockException extends RuntimeException
{
	public OutOfStockException(final String message)
	{
		super(message);
	}
}
