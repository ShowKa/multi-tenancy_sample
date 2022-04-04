package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.auth.Role

enum class UserRole(private val roleName: String) : Role {
	Administrator("Administrator"),
	Operator("Operator"),
	;

	override fun getName(): String {
		return this.roleName
	}
}