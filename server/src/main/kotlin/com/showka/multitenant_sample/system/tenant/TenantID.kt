package com.showka.multitenant_sample.system.tenant

import java.util.*

class TenantID {

	val value: Long

	// constructor
	constructor (id: Long) {
		value = id
	}

	constructor(id: Int) {
		value = id.toLong()
	}

	// override
	override fun toString(): String {
		return this.value.toString()
	}

	override fun equals(other: Any?): Boolean {
		if (other == null) {
			return false
		}
		return if (other is TenantID) {
			this.value == other.value
		} else {
			false
		}
	}

	override fun hashCode(): Int {
		return this.value.hashCode()
	}

	// static
	companion object {
		fun generateByOrgId(orgId: String): TenantID {
			val hash = UUID.nameUUIDFromBytes(orgId.toByteArray()).mostSignificantBits
			return TenantID(hash)
		}

		fun generateEmpty(): TenantID {
			return TenantID(0L)
		}
	}
}
