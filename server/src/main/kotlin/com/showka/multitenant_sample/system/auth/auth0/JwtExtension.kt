package com.showka.multitenant_sample.system.auth.auth0

import org.springframework.security.oauth2.jwt.Jwt

fun Jwt.getOrganizationId(): String? {
	return this.getClaimAsString("org_id")
}
