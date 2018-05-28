package com.bdtv.ms.ecom.cart.service.feign;

import com.bdtv.ms.ecom.cart.service.data.Stock;
import com.bdtv.ms.ecom.cart.service.feign.hystrix.StockFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dangao on 5/28/2018.
 * @version 1.0
 */

@FeignClient(name = "microservice.stock.service", fallback = StockFeignClientHystrix.class)
public interface StockFeignClient
{
	@GetMapping("/stockapi/stock")
	ResponseEntity<Stock> getStockByProductId(@RequestParam(value = "productId") final Long productId);
}
