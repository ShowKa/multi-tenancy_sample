package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.auth.PermissionService
import com.showka.multitenant_sample.system.auth.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun get(role: Role): List<String> {
		val roleId = role.getId()
		val permissions = managementApi.roles().listPermissions(roleId, null).execute()
		return permissions.items.map { it.name }
	}
}
