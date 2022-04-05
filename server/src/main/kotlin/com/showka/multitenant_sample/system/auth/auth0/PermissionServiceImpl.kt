package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.auth.PermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun get(roleId: String): List<String> {
		val permissions = managementApi.roles().listPermissions(roleId, null).execute()
		return permissions.items.map { it.name }
	}
}
