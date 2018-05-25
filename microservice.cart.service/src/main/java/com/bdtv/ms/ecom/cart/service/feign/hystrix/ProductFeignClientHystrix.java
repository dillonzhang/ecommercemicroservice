package com.bdtv.ms.ecom.cart.service.feign.hystrix;

import com.bdtv.ms.ecom.cart.service.data.Product;
import com.bdtv.ms.ecom.cart.service.feign.ProductFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * @author dangao on 5/11/2018.
 * @version 1.0
 */
@Component
public class ProductFeignClientHystrix implements ProductFeignClient
{
	private static final Logger logger = LoggerFactory.getLogger(ProductFeignClientHystrix.class);
	@Override
	public ResponseEntity<Product> findById(final Long id)
	{
		logger.error("Don't have available product service");
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
	}
}
