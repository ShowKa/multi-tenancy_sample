package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.auth.Role
import com.showka.multitenant_sample.system.auth.RoleAssignService
import com.showka.multitenant_sample.system.auth.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleAssignServiceImpl : RoleAssignService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	@Autowired
	private lateinit var roleService: RoleService

	override fun assign(role: Role, userIds: List<String>) {
		val roleId = roleService.getId(role)
		managementApi.roles().assignUsers(roleId, userIds).execute()
	}

	override fun assign(roleList: List<Role>, userId: String, organizationId: String) {
		val roleIds = roleList.map { roleService.getId(it) }
		val roles = Roles(roleIds)
		managementApi.organizations().addRoles(organizationId, userId, roles).execute()
	}
}
