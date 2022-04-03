package com.showka.multitenant_sample.system.authentification

interface RoleAssignService {

	fun assign(role: Role, userClaimList: List<Claim>)

	fun assign(roleList: List<Role>, userClaim: Claim, organization: Organization)

	// default
	fun assign(role: Role, userClaim: Claim) {
		this.assign(role, listOf(userClaim))
	}

	fun assign(roleList: List<Role>, userClaim: Claim) {
		roleList.forEach {
			this.assign(it, userClaim)
		}
	}

	fun assign(roleList: List<Role>, userClaimList: List<Claim>) {
		roleList.forEach {
			this.assign(it, userClaimList)
		}
	}

	fun assign(role: Role, userClaim: Claim, organization: Organization) {
		this.assign(listOf(role), userClaim, organization)
	}
}
