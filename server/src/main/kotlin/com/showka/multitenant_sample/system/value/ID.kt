package com.showka.multitenant_sample.system.value

import java.io.Serializable
import java.security.SecureRandom

open class ID : Serializable {

	val value: Long

	// Caution: String constructor must be first constructor.
	// to convert string value to id in form
	constructor(id: String) {
		this.value = id.toLong()
	}

	constructor() : this(SecureRandom().nextLong())
	constructor(value: Int) : this(value.toLong())
	constructor(value: Long) {
		this.value = value
	}

	// override
	override fun toString(): String {
		return this.value.toString()
	}

	override fun equals(other: Any?): Boolean {
		if (other == null) {
			return false
		}
		return if (other is ID) {
			this.value == other.value
		} else {
			false
		}
	}

	override fun hashCode(): Int {
		return this.value.hashCode()
	}
}
