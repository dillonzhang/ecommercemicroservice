package com.bdtv.ms.ecom.cart.service.config;



import com.bdtv.ms.ecom.cart.service.feign.filter.FeignBasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author dangao on 5/29/2018.
 * @version 1.0
 */
@Configuration
public class FeignConfiguration
{
	@Bean
	public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new FeignBasicAuthRequestInterceptor();
	}
}
