package com.showka.multitenant_sample.app

import org.springframework.security.oauth2.jwt.Jwt

fun Jwt.getOrganizationId(): String? {
	return this.getClaimAsString("org_id")
}

fun Jwt.getClientId(): String {
	return this.getClaimAsString("azp")
}
