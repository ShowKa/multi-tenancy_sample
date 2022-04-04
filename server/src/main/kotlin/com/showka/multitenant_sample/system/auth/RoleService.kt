package com.showka.multitenant_sample.system.auth

interface RoleService {
	fun getRoleId(role: Role): String
}
