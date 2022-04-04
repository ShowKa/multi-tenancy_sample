package com.showka.multitenant_sample.system.authentification.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.authentification.Permission
import com.showka.multitenant_sample.system.authentification.PermissionService
import com.showka.multitenant_sample.system.authentification.Role
import com.showka.multitenant_sample.system.authentification.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	@Autowired
	private lateinit var roleService: RoleService

	override fun getPermissions(role: Role): List<Permission> {
		val roleId = roleService.getRoleId(role)
		val permissions = managementApi.roles().listPermissions(roleId, null).execute()
		return permissions.items.map {
			Permission(it.name)
		}
	}
}
