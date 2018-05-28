package com.bdtv.ms.ecom.stock.service.service;

import com.bdtv.ms.ecom.stock.service.entity.Stock;


/**
 * @author dangao on 5/24/2018.
 * @version 1.0
 */
public interface StockService
{
	Stock getStockByProductId(final Long productId);

	Stock createStock(final Long productId, final Long stockLevel);

	Stock updateStock(final Long productId, final Long stockLevel);

	void deleteStock(final Long productId);
}
