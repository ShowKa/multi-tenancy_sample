package com.showka.multitenant_sample.system.authentification.auth0

import com.showka.multitenant_sample.system.authentification.Invitation
import com.showka.multitenant_sample.system.authentification.Organization
import com.showka.multitenant_sample.system.authentification.Role
import com.showka.multitenant_sample.system.value.MailAddress

class OrganizationInvitation(
	override val clientId: String,
	override val organization: Organization,
	override val inviterName: String,
	override val inviteeMailAddress: MailAddress,
) : Invitation {
	override val roles: MutableList<Role> = mutableListOf()
}
