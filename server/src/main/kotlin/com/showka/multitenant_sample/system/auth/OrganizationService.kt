package com.showka.multitenant_sample.system.auth

interface OrganizationService {

	// interface
	/**
	 * create organization.
	 */
	fun create(identifierName: String, displayName: String): Organization

	/**
	 * rename organization display name.
	 */
	fun updateDisplayName(organizationId: String, newName: String): Organization

	/**
	 * get organization
	 */
	fun get(organizationId: String): Organization

	/**
	 * get organization by name.
	 */
	fun getByName(identifiedName: String): Organization

	/**
	 * get User's organization
	 * @param userId subject of user claim.
	 */
	fun getBelongsTo(userId: String): List<Organization>

	// default
	/**
	 * get User's organization
	 */
	fun getBelongsTo(user: User): List<Organization> {
		return getBelongsTo(user.id)
	}

	/**
	 * rename organization display name.
	 */
	fun updateDisplayName(organization: Organization): Organization {
		return updateDisplayName(organization.id, organization.displayName)
	}
}
