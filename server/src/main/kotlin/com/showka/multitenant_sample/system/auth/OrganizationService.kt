package com.showka.multitenant_sample.system.auth

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
	fun getBelongsTo(user: User): List<Organization> {
		return getBelongsTo(user.id)
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
