package com.showka.multitenant_sample.system.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

	@Value("\${auth0.audience}")
	private lateinit var audience: String

	@Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private lateinit var issuer: String

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
			.authorizeRequests()
			.anyRequest().authenticated()
		http
			.oauth2ResourceServer()
			.jwt()
			.decoder(jwtDecoder())
			.jwtAuthenticationConverter(jwtAuthenticationConverter())
		http.csrf().disable()
		http.headers().frameOptions().sameOrigin()
	}

	/**
	 * Allow CORS
	 */
	@Bean
	fun simpleCorsFilter(): FilterRegistrationBean<CorsFilter> {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.run {
			// CAUTION Since SpringBoot 2.4.0
			// When allowCredentials is true, allowedOrigins cannot contain the special value "*"
			allowCredentials = false
			allowedOrigins = Collections.singletonList("*")
			allowedMethods = Collections.singletonList("*")
			allowedHeaders = Collections.singletonList("*")
		}
		source.registerCorsConfiguration("/**", config)
		val bean: FilterRegistrationBean<CorsFilter> = FilterRegistrationBean(CorsFilter(source))
		bean.order = Ordered.HIGHEST_PRECEDENCE
		return bean
	}

	private fun jwtDecoder(): JwtDecoder {
		val withAudience = AudienceValidator(audience)
		val withIssuer = JwtValidators.createDefaultWithIssuer(issuer)
		val validator = DelegatingOAuth2TokenValidator(withAudience, withIssuer)
		val jwtDecoder = JwtDecoders.fromOidcIssuerLocation<JwtDecoder>(issuer) as NimbusJwtDecoder
		jwtDecoder.setJwtValidator(validator)
		return jwtDecoder
	}

	private fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
		val converter = JwtGrantedAuthoritiesConverter()
		converter.setAuthoritiesClaimName("permissions")
		converter.setAuthorityPrefix("")
		val jwtConverter = JwtAuthenticationConverter()
		jwtConverter.setJwtGrantedAuthoritiesConverter(converter)
		return jwtConverter
	}
}
