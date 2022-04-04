package com.showka.multitenant_sample.system.auth

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
	fun add(organization: Organization, user: User) {
		add(organization.id, user.id)
	}

	/**
	 * add members.
	 * @param userIdList subject of user claim.
	 */
	fun add(organizationId: String, userIdList: List<String>)

	/**
	 * assign role to organization's member.
	 */
	fun assign(organizationId: String, userId: String, roleId: String)
}
