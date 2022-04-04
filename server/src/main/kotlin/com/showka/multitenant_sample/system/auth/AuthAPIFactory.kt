package com.showka.multitenant_sample.system.auth

import com.auth0.client.HttpOptions
import com.auth0.client.auth.AuthAPI
import com.auth0.client.mgmt.ManagementAPI
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthAPIFactory {

	@Value("\${auth0.domain}")
	private lateinit var domain: String

	@Value("\${auth0.client-id}")
	private lateinit var clientId: String

	@Value("\${auth0.client-secret}")
	private lateinit var clientSecret: String

	@Bean
	fun beanAuthAPI(): AuthAPI {
		val options = HttpOptions()
		options.connectTimeout = 10
		options.readTimeout = 20
		return AuthAPI(domain, clientId, clientSecret, options)
	}

	@Bean
	fun beanAuthManagementAPI(): ManagementAPI {
		val options = HttpOptions()
		options.connectTimeout = 10
		options.readTimeout = 20
		val authAPI = AuthAPI(domain, clientId, clientSecret)
		val authRequest = authAPI.requestToken("https://${domain}/api/v2/")
		val holder = authRequest.execute()
		return ManagementAPI(domain, holder.accessToken, options)
	}
}
