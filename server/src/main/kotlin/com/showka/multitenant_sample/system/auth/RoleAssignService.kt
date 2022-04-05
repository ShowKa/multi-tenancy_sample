package com.showka.multitenant_sample.system.auth

interface RoleAssignService {

	// interface
	fun assign(roleId: String, userIds: List<String>)

	fun assign(roleIds: List<String>, userId: String, organizationId: String)

	// default
	fun assign(role: Role, user: User) {
		assignUsers(role, listOf(user))
	}

	fun assignUsers(role: Role, userList: List<User>) {
		assign(role.getId(), userList.map { it.id })
	}

	fun assign(role: Role, user: User, organization: Organization) {
		assign(listOf(role), user, organization)
	}

	fun assign(roleList: List<Role>, user: User, organization: Organization) {
		assign(roleList.map { it.getId() }, user.id, organization.id)
	}
}
