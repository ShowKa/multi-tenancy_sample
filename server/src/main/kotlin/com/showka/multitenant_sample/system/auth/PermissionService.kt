package com.showka.multitenant_sample.system.auth

interface PermissionService {
	fun get(role: Role): List<String>
}
