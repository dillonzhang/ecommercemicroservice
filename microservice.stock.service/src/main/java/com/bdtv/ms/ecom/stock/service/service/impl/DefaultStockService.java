package com.bdtv.ms.ecom.stock.service.service.impl;

import com.bdtv.ms.ecom.stock.service.entity.Stock;
import com.bdtv.ms.ecom.stock.service.repository.StockLevelRepository;
import com.bdtv.ms.ecom.stock.service.service.StockService;
import com.bdtv.ms.ecom.stock.service.service.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author dangao on 5/24/2018.
 * @version 1.0
 */
@Service
@Transactional
public class DefaultStockService implements StockService
{
	@Autowired
	private StockLevelRepository stockLevelRepository;
	@Override
	public Stock getStockByProductId(final Long productId)
	{
		Stock stock = stockLevelRepository.getStockByProductId(productId);
		if(stock == null)
		{
			throw new StockNotFoundException("The Product[" + productId + "] have no stock data.");
		}
		return stock;
	}

	@Override
	public Stock createStock(final Long productId, final Long stockLevel)
	{
		Stock stock = new Stock();
		stock.setProductId(productId);
		stock.setStockLevel(stockLevel);
		return stockLevelRepository.save(stock);
	}

	@Override
	public Stock updateStock(final Long productId, final Long stockLevel)
	{
		Stock stock = this.getStockByProductId(productId);
		stock.setStockLevel(stockLevel);
		return stockLevelRepository.saveAndFlush(stock);
	}

	@Override
	public void deleteStock(final Long productId)
	{
		stockLevelRepository.deleteStockByProductId(productId);
	}
}
