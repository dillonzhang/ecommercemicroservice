package com.bdtv.ms.ecom.product.service.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.bdtv.ms.ecom.product.service.data.ProductChange;

@Component
public class SimpleSourceBean {
	private Source source;

	private static final Logger logger = LoggerFactory
			.getLogger(SimpleSourceBean.class);

	public SimpleSourceBean(Source source) {
		this.source = source;
	}

	public void publishProductChange(String action, String productId) {
		logger.debug("Sending RabbitMQ message {} for Product ID : {}", action,
				productId);

		ProductChange pchange = new ProductChange(
				ProductChange.class.getTypeName(), action, productId);

		source.output().send(MessageBuilder.withPayload(pchange).build());
	}
}
