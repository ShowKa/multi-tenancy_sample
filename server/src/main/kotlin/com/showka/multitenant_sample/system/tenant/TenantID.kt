package com.showka.multitenant_sample.system.tenant

import com.showka.multitenant_sample.system.value.ID
import java.util.*

class TenantID : ID {

	constructor() : super()
	constructor(id: Int) : super(id.toLong())
	constructor(id: Long) : super(id)
	constructor(id: String) : super(id)

	companion object {

		fun generateByOrgId(orgId: String): TenantID {
			val hash = UUID.nameUUIDFromBytes(orgId.toByteArray()).mostSignificantBits
			return TenantID(hash)
		}

		fun generateEmpty(): TenantID {
			return TenantID(0)
		}
	}
}
