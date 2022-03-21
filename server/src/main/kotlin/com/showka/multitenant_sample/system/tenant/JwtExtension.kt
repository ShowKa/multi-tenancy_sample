package com.showka.multitenant_sample.system.tenant

import org.springframework.security.oauth2.jwt.Jwt

private const val namespace = "http://multitenant_sample.showka.com"

fun Jwt.getTenantId(): TenantID? {
	val tenantId = this.getClaim<String>("${namespace}/tenant_id")
	return if (tenantId != null) {
		TenantID(tenantId)
	} else null
}
