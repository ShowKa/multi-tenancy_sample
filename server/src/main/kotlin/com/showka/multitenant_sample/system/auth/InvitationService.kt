package com.showka.multitenant_sample.system.auth

interface InvitationService {

	// interface
	fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleIds: List<String>
	): String

	// default
	fun invite(invitation: Invitation): String {
		return this.invite(
			invitation.clientId, invitation.organization.id,
			invitation.inviterName, invitation.inviteeMailAddress.string,
			invitation.roles.map { it.getId() }
		)
	}

	fun invite(
		clientId: String,
		organizationId: String,
		inviterName: String,
		inviteeMailAddress: String
	): String {
		return this.invite(
			clientId, organizationId,
			inviterName, inviteeMailAddress,
			listOf()
		)
	}

	fun invite(
		clientId: String,
		organizationId: String,
		inviterName: String,
		inviteeMailAddress: String,
		roleId: String
	): String {
		return this.invite(
			clientId, organizationId,
			inviterName, inviteeMailAddress,
			listOf(roleId)
		)
	}
}
