package com.showka.multitenant_sample.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

	@Autowired
	private lateinit var tenantInterceptor: TenantInterceptor

	// add interceptors
	override fun addInterceptors(registry: InterceptorRegistry) {
		registry.addInterceptor(tenantInterceptor)
	}
}
