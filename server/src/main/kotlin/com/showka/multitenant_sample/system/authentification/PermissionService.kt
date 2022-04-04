package com.showka.multitenant_sample.system.authentification

interface PermissionService {
	fun getPermissions(role: Role): List<Permission>
}
