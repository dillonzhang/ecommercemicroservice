package com.bdtv.ms.ecom.stock.service.service.exception;

/**
 * @author dangao on 5/11/2018.
 * @version 1.0
 */
public class StockNotFoundException extends RuntimeException
{
	public StockNotFoundException()
	{
		super();
	}

	public StockNotFoundException(final String message)
	{
		super(message);
	}
}
