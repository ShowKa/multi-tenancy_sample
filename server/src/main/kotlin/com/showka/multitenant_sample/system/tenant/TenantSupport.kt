package com.showka.multitenant_sample.system.tenant

interface TenantSupport {
	fun setTenantId(tenantId: TenantID)
	fun isLegalTenant(expectedTenantId: TenantID): Boolean
}
