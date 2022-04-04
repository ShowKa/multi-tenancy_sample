package com.showka.multitenant_sample.system.auth

interface RoleService {
	fun getId(role: Role): String
}
