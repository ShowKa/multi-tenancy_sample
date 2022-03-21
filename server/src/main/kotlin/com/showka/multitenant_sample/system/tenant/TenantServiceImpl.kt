package com.showka.multitenant_sample.system.tenant

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.users.User
import com.showka.multitenant_sample.system.authentification.Claim
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TenantServiceImpl : TenantService {

	companion object {
		private const val KEY = "tenant_id"
	}

	// dependency
	@Autowired
	private lateinit var managementApi: ManagementAPI

	// override
	override fun save(userClaim: Claim, tenantId: TenantID) {
		val api = managementApi.users()
		val userId = userClaim.subject
		// it's ok to create new user instance.
		// properties of user_metadata will be merged (= whole metadata will not be updated, not overridden)
		val user = User()
		user.userMetadata = hashMapOf<String, Any>()
		user.userMetadata[KEY] = tenantId.toString()
		api.update(userId, user).execute()
	}

	override fun get(userClaim: Claim): TenantID? {
		val api = managementApi.users()
		val userId = userClaim.subject
		val user = api.get(userId, null).execute()
		if (user.userMetadata == null) {
			return null
		}
		val tenantId = user.userMetadata[KEY]
		return if (tenantId != null) {
			TenantID(tenantId as String)
		} else null
	}
}
