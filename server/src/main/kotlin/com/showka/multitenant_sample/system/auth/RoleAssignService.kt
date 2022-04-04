package com.showka.multitenant_sample.system.auth

interface RoleAssignService {

	fun assign(role: Role, userList: List<User>)

	fun assign(roleList: List<Role>, user: User, organization: Organization)

	// default
	fun assign(role: Role, user: User) {
		this.assign(role, listOf(user))
	}

	fun assign(roleList: List<Role>, user: User) {
		roleList.forEach {
			this.assign(it, user)
		}
	}

	fun assign(roleList: List<Role>, userList: List<User>) {
		roleList.forEach {
			this.assign(it, userList)
		}
	}

	fun assign(role: Role, user: User, organization: Organization) {
		this.assign(listOf(role), user, organization)
	}
}
