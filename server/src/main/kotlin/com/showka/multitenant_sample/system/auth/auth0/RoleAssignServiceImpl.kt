package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.auth.RoleAssignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleAssignServiceImpl : RoleAssignService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun assign(roleId: String, userIds: List<String>) {
		managementApi.roles().assignUsers(roleId, userIds).execute()
	}

	override fun assign(roleIds: List<String>, userId: String, organizationId: String) {
		val roles = Roles(roleIds)
		managementApi.organizations().addRoles(organizationId, userId, roles).execute()
	}
}
