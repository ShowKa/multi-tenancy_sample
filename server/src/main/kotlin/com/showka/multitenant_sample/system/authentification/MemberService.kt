package com.showka.multitenant_sample.system.authentification

interface MemberService {

	/**
	 * get members of the organization.
	 */
	fun getOf(organizationId: String): List<Member>

	/**
	 * get members of the organization.
	 */
	fun getOf(organization: Organization): List<Member> {
		return getOf(organization.id)
	}

	/**
	 * add member.
	 * @param userId subject of user claim.
	 */
	fun add(organizationId: String, userId: String) {
		add(organizationId, listOf(userId))
	}

	/**
	 * add member.
	 */
	fun add(organization: Organization, userClaim: Claim) {
		add(organization.id, userClaim.subject)
	}

	/**
	 * add members.
	 * @param userIdList subject of user claim.
	 */
	fun add(organizationId: String, userIdList: List<String>)
}
