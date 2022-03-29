package com.showka.multitenant_sample.system.authentification

interface InvitationService {

	fun invite(invitation: Invitation): String

	fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String
	): String

	fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleIds: List<String>
	): String
}
