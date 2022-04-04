package com.showka.multitenant_sample.system.auth

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Roles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleAssignServiceImpl : RoleAssignService {

	// dependency
	@Autowired
	private lateinit var managementApi: ManagementAPI

	@Autowired
	private lateinit var roleService: RoleService

	// override
	override fun assign(role: Role, userList: List<User>) {
		val roleId = roleService.getRoleId(role)
		val userIds = userList.map { it.id } // auth api's user ID = subject
		managementApi.roles().assignUsers(roleId, userIds).execute()
	}

	override fun assign(roleList: List<Role>, user: User, organization: Organization) {
		val roleIds = roleList.map { roleService.getRoleId(it) }
		val roles = Roles(roleIds)
		managementApi.organizations().addRoles(organization.id, user.id, roles).execute()
	}
}