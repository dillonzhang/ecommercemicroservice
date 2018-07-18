package com.bdtv.ms.ecom.order.service.events.handles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.bdtv.ms.ecom.order.service.data.Product;
import com.bdtv.ms.ecom.order.service.data.ProductChange;
import com.bdtv.ms.ecom.order.service.events.CustomChannels;
import com.bdtv.ms.ecom.order.service.repository.ProductRedisRepository;

@EnableBinding(CustomChannels.class)
public class ProductChangeHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(ProductChangeHandler.class);

	@Autowired
	private ProductRedisRepository productRedisRep;

	@StreamListener("inboundProductChanges")
	public void loggerSink(ProductChange productChange) {
		logger.info("Received an event for  id {}",
				productChange.getProductId());

		switch (productChange.getAction()) {
			case "GET" :
				logger.debug(
						"Received a GET event from the product service for product id {}",
						productChange.getProductId());
				break;
			case "SAVE" :
				logger.debug(
						"Received a SAVE event from the product service for product id {}",
						productChange.getProductId());
				break;
			case "UPDATE" :
				logger.debug(
						"Received a UPDATE event from the product service for product id {}",
						productChange.getProductId());
				Product p = new Product();
				p.setId(Long.valueOf(productChange.getProductId()));
				productRedisRep.deleteProduct(p);
				break;
			case "DELETE" :
				logger.debug(
						"Received a DELETE event from the product service for product id {}",
						productChange.getProductId());
				Product p1 = new Product();
				p1.setId(Long.valueOf(productChange.getProductId()));
				productRedisRep.deleteProduct(p1);
				break;
			default :
				logger.error(
						"Received an UNKNOWN event from the product service of type {}",
						productChange.getType());
				break;

		}
	}
}
