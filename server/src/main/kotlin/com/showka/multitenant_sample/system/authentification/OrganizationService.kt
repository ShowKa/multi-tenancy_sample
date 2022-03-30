package com.showka.multitenant_sample.system.authentification

interface OrganizationService {

	/**
	 * create organization.
	 */
	fun create(identifierName: String, displayName: String): Organization

	/**
	 * get organization
	 */
	fun get(organizationId: String): Organization

	/**
	 * get User's organization
	 * @param userId subject of user claim.
	 */
	fun getBelongsTo(userId: String): List<Organization>

	/**
	 * get User's organization
	 */
	fun getBelongsTo(userClaim: Claim): List<Organization> {
		return getBelongsTo(userClaim.subject)
	}

	/**
	 * add member.
	 * @param userId subject of user claim.
	 */
	fun addMember(organizationId: String, userId: String)

	/**
	 * add member.
	 */
	fun addMember(organization: Organization, userClaim: Claim) {
		addMember(organization.id, userClaim.subject)
	}

	/**
	 * add members.
	 * @param userIdList subject of user claim.
	 */
	fun addMembers(organizationId: String, userIdList: List<String>)

	/**
	 * add members.
	 */
	fun addMembers(organization: Organization, userList: List<Claim>) {
		addMembers(organization.id, userList.map { it.subject })
	}

	/**
	 * get organization by name.
	 */
	fun getByName(identifiedName: String): Organization

	/**
	 * rename organization display name.
	 */
	fun updateDisplayName(organizationId: String, newName: String): Organization

	/**
	 * rename organization display name.
	 */
	fun updateDisplayName(organization: Organization): Organization {
		return updateDisplayName(organization.id, organization.displayName)
	}
}
