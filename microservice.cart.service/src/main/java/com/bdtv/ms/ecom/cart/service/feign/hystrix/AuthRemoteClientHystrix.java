package com.bdtv.ms.ecom.cart.service.feign.hystrix;

import com.bdtv.ms.ecom.cart.service.feign.AuthRemoteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;


/**
 * @author dangao on 5/29/2018.
 * @version 1.0
 */
@Component
public class AuthRemoteClientHystrix implements AuthRemoteClient
{
	private static final Logger logger = LoggerFactory.getLogger(ProductFeignClientHystrix.class);

	@Override
	public ResponseEntity<OAuth2AccessToken> auth(final String client_id, final String client_secret, final String grant_type, final String scop)
	{
		logger.error("Don't have available auth service");
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
	}
}
