package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Invitee
import com.auth0.json.mgmt.organizations.Inviter
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.auth.InvitationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.auth0.json.mgmt.organizations.Invitation as Auth0_Invitation

@Service
class InvitationServiceImpl : InvitationService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleIds: List<String>
	): String {
		// invitation for auth0
		val inviter = Inviter(inviterName)
		val invitee = Invitee(inviteeMailAddress)
		val auth0Invitation = Auth0_Invitation(inviter, invitee, clientId)
		// set role id
		auth0Invitation.roles = Roles(roleIds)
		val result =
			managementApi.organizations().createInvitation(organizationId, auth0Invitation).execute()
		return result.id
	}
}
