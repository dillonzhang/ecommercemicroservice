package com.bdtv.ms.ecom.auth.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig
		extends
			AuthorizationServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Bean
	public RedisTokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		// password 方案三：支持多种编码，通过密码的前缀区分编码方式
		String finalSecret = "{bcrypt}"
				+ new BCryptPasswordEncoder().encode("123456");
		// 配置两个客户端,一个用于password认证一个用于client认证
		clients.inMemory().withClient("client_1")
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("select").authorities("oauth2").secret(finalSecret)
				.and().withClient("client_2")
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("select").authorities("oauth2").secret(finalSecret);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
				.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET,
						HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
	}

}
