package com.bdtv.ms.ecom.cart.service.feign;

import com.bdtv.ms.ecom.cart.service.feign.hystrix.AuthRemoteClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dangao on 5/29/2018.
 * @version 1.0
 */

@FeignClient(value = "microservice.auth.service", fallback = AuthRemoteClientHystrix.class)
public interface AuthRemoteClient
{
	@PostMapping("/oauth/token")
	ResponseEntity<OAuth2AccessToken> auth(@RequestParam(name = "client_id") final String client_id,
			@RequestParam(name = "client_secret") final String client_secret,
			@RequestParam(name = "grant_type") final String grant_type, @RequestParam(name = "scop") final String scop);
}
