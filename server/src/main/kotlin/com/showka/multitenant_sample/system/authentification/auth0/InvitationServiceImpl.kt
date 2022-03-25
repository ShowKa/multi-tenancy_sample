package com.showka.multitenant_sample.system.authentification.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Invitee
import com.auth0.json.mgmt.organizations.Inviter
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.authentification.Invitation
import com.showka.multitenant_sample.system.authentification.InvitationService
import com.showka.multitenant_sample.system.authentification.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.auth0.json.mgmt.organizations.Invitation as Auth0_Invitation

@Service
class InvitationServiceImpl : InvitationService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	@Autowired
	private lateinit var roleService: RoleService

	override fun invite(invitation: Invitation): String {
		// get role id
		val roleIds = mutableListOf<String>()
		invitation.roles?.forEach {
			val id = roleService.getRoleId(it)
			roleIds.add(id)
		}
		// do
		return this.invite(
			invitation.clientId, invitation.organization.id,
			invitation.inviterName, invitation.inviteeMailAddress.string,
			roleIds
		)
	}

	override fun invite(
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

	override fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleIds: List<String>
	): String {
		// invitation for auth0
		val inviter = Inviter(inviterName)
		val invitee = Invitee(inviteeMailAddress)
		val auth0Invitation = Auth0_Invitation(inviter, invitee, clientId)
		// role
		if (roleIds.isNotEmpty()) {
			auth0Invitation.roles = Roles(roleIds)
		}
		val result =
			managementApi.organizations().createInvitation(organizationId, auth0Invitation).execute()
		return result.id
	}
}
