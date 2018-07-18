package com.bdtv.ms.ecom.order.service.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
	@Input("inboundProductChanges")
	SubscribableChannel products();
}
