package com.showka.multitenant_sample.system.auth

interface PermissionService {
	fun get(roleId: String): List<String>
	fun get(role: Role): List<String> {
		return get(role.getId())
	}
}
