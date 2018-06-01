package com.bdtv.ms.ecom.cart.service.feign.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;


/**
 * @author dangao on 5/29/2018.
 * @version 1.0
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor
{
	public FeignBasicAuthRequestInterceptor()
	{

	}

	@Override
	public void apply(final RequestTemplate requestTemplate)
	{
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		StringBuilder accessToken = new StringBuilder("Bearer");
		if (authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
			OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication
					.getDetails();
			accessToken.append(" ").append(details.getTokenValue());
		}
		requestTemplate.header("Authorization", accessToken.toString());
	}
}
