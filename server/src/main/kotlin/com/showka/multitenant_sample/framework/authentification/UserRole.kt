package com.showka.multitenant_sample.framework.authentification

import com.showka.multitenant_sample.system.authentification.Role

enum class UserRole(private val roleName: String) : Role {
	Administrator("Administrator"),
	Operator("Operator"),
	;

	override fun getName(): String {
		return this.roleName
	}
}
