package com.bdtv.ms.ecom.cart.service.feign.hystrix;

import com.bdtv.ms.ecom.cart.service.data.Product;
import com.bdtv.ms.ecom.cart.service.feign.ProductFeignClient;
import com.bdtv.ms.ecom.cart.service.service.exception.ServiceUnavailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * @author dangao on 5/11/2018.
 * @version 1.0
 */
@Component
public class ProductFeignClientHystrix implements ProductFeignClient
{
	@Override
	public ResponseEntity<Product> findById(final Long id)
	{
		throw new ServiceUnavailableException("Current product Service is unavailable");
	}
}
