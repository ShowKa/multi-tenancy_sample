package com.showka.multitenant_sample.system.authentification

interface RoleAssignService {
	fun assign(role: Role, userClaim: Claim)
	fun assign(roleList: List<Role>, userClaim: Claim)
	fun assign(role: Role, userClaimList: List<Claim>)
	fun assign(roleList: List<Role>, userClaimList: List<Claim>)
	fun assign(roleList: List<Role>, userClaim: Claim, organization: Organization)
	fun assign(role: Role, userClaim: Claim, organization: Organization)
}
