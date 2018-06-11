package com.bdtv.ms.ecom.cart.service.feign;

import com.bdtv.ms.ecom.cart.service.config.FeignConfiguration;
import com.bdtv.ms.ecom.cart.service.data.Product;
import com.bdtv.ms.ecom.cart.service.data.Stock;
import com.bdtv.ms.ecom.cart.service.feign.hystrix.ProductFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dangao on 5/11/2018.
 * @version 1.0
 */

@FeignClient(name = "microservice.product.service", configuration = FeignConfiguration.class, fallback = ProductFeignClientHystrix.class)
public interface ProductFeignClient
{
	@GetMapping("/productapi/product/{id}")
	ResponseEntity<Product> findById(@PathVariable("id") final Long id);
}
