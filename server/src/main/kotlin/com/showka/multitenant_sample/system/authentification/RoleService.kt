package com.showka.multitenant_sample.system.authentification

interface RoleService {
	fun getRoleId(role: Role): String
	fun getPermissions(role: Role): List<Permission>
}
