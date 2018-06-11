package com.bdtv.ms.ecom.cart.service.feign.hystrix;

import com.bdtv.ms.ecom.cart.service.data.Stock;
import com.bdtv.ms.ecom.cart.service.feign.StockFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * @author dangao on 5/28/2018.
 * @version 1.0
 */
@Component
public class StockFeignClientHystrix implements StockFeignClient
{
	private static final Logger logger = LoggerFactory.getLogger(StockFeignClientHystrix.class);

	@Override
	public ResponseEntity<Stock> getStockByProductId(final Long productId)
	{
		logger.error("Don't have available stock service");
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
	}
}
