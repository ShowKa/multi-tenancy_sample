package com.showka.multitenant_sample.system.auth

interface MemberService {

	// interface
	/**
	 * get members of the organization.
	 */
	fun getOf(organizationId: String): List<Member>

	/**
	 * add members.
	 * @param userIdList subject of user claim.
	 */
	fun add(organizationId: String, userIdList: List<String>)

	// default
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
	fun add(organization: Organization, user: User) {
		add(organization.id, user.id)
	}
}
