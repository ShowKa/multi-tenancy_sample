package com.showka.multitenant_sample.system.security

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt


class AudienceValidator(private val audience: String) : OAuth2TokenValidator<Jwt> {

	init {
		assert(audience.isNotBlank()) {
			"audience must not be blank"
		}
	}

	override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
		val audiences = jwt.audience
		if (audiences.contains(this.audience)) {
			return OAuth2TokenValidatorResult.success()
		}
		val err = OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN)
		return OAuth2TokenValidatorResult.failure(err)
	}
}
