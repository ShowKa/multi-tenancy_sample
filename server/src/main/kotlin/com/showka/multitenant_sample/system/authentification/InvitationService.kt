package com.showka.multitenant_sample.system.authentification

interface InvitationService {

	fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleList: List<Role>
	): String

	// default
	fun invite(invitation: Invitation): String {
		return this.invite(
			invitation.clientId, invitation.organization.id,
			invitation.inviterName, invitation.inviteeMailAddress.string,
			invitation.roles
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
		role: Role
	): String {
		return this.invite(
			clientId, organizationId,
			inviterName, inviteeMailAddress,
			listOf(role)
		)
	}
}
