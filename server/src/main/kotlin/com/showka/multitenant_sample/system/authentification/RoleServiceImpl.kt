package com.showka.multitenant_sample.system.authentification

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.exception.SystemException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.auth0.json.mgmt.Role as Auth0_Role

@Service
class RoleServiceImpl : RoleService {

	// dependency
	@Autowired
	private lateinit var managementApi: ManagementAPI

	/** role map < role's name, role's ID > */
	private val roleMap: HashMap<String, Auth0_Role> = hashMapOf()

	override fun getRoleId(role: Role): String {
		return this.getRole(role).id
	}

	override fun getPermissions(role: Role): List<Permission> {
		val roleId = this.getRoleId(role)
		val permissions = managementApi.roles().listPermissions(roleId, null).execute()
		return permissions.items.map {
			Permission(it.name)
		}
	}

	// private
	private fun getRole(role: Role): Auth0_Role {
		if (roleMap.isEmpty()) {
			// if empty -> cash role's information
			val rolesEntity = managementApi.roles().list(null).execute()
			rolesEntity.items.forEach {
				this.roleMap[it.name] = it
			}
		}
		return roleMap[role.getName()]
			?: throw SystemException(
				"ロールが存在しません: ${role.getName()}: \n" +
					"Auth Management APIより取得したロールは次のとおりです。: $roleMap"
			)
	}
}
