package com.showka.multitenant_sample.system.auth

interface PermissionService {
	fun getPermissions(role: Role): List<Permission>
}
