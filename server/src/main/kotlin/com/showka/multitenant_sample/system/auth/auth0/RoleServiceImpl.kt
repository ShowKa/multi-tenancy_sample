package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.auth.RoleService
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

	override fun getId(name: String): String {
		return this.getRole(name).id
	}

	// private
	private fun getRole(name: String): Auth0_Role {
		if (roleMap.isEmpty()) {
			// if empty -> cash role's information
			val rolesEntity = managementApi.roles().list(null).execute()
			rolesEntity.items.forEach {
				this.roleMap[it.name] = it
			}
		}
		return roleMap[name]
			?: throw SystemException(
				"ロールが存在しません: ${name}: \n" +
					"Auth Management APIより取得したロールは次のとおりです。: $roleMap"
			)
	}
}
