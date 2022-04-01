package com.showka.multitenant_sample.system.tenant

import com.showka.multitenant_sample.system.value.ID

class TenantID : ID {

	constructor() : super()
	constructor(id: Int) : super(id.toLong())
	constructor(id: Long) : super(id)
	constructor(id: String) : super(id)

	companion object {
		// FIXME
		fun generateByOrgId(orgId: String): TenantID {
			return TenantID(orgId.hashCode())
		}

		fun generateEmpty(): TenantID {
			return TenantID(0)
		}
	}
}
