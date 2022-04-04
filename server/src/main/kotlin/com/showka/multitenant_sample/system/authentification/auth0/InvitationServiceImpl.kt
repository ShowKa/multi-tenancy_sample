package com.showka.multitenant_sample.system.authentification.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Invitee
import com.auth0.json.mgmt.organizations.Inviter
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.authentification.InvitationService
import com.showka.multitenant_sample.system.authentification.Role
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

	override fun invite(
		clientId: String, organizationId: String,
		inviterName: String, inviteeMailAddress: String,
		roleList: List<Role>
	): String {
		// invitation for auth0
		val inviter = Inviter(inviterName)
		val invitee = Invitee(inviteeMailAddress)
		val auth0Invitation = Auth0_Invitation(inviter, invitee, clientId)
		// get role id
		val roleIds = mutableListOf<String>()
		roleList.forEach {
			val id = roleService.getRoleId(it)
			roleIds.add(id)
		}
		auth0Invitation.roles = Roles(roleIds)
		val result =
			managementApi.organizations().createInvitation(organizationId, auth0Invitation).execute()
		return result.id
	}
}
