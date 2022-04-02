package com.showka.multitenant_sample.system.tenant

class TenantContext {
	companion object {
		private val tenantHolder: ThreadLocal<TenantID> = InheritableThreadLocal()

		fun get(): TenantID {
			return tenantHolder.get()
		}

		fun set(tenantId: TenantID) {
			tenantHolder.set(tenantId)
		}

		fun clear() {
			tenantHolder.set(null)
		}
	}
}
