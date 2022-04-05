package com.showka.multitenant_sample.system.auth.auth0

import com.showka.multitenant_sample.system.auth.Invitation
import com.showka.multitenant_sample.system.auth.Organization
import com.showka.multitenant_sample.system.auth.Role
import com.showka.multitenant_sample.system.value.MailAddress

class OrganizationInvitation(
	override val clientId: String,
	override val organization: Organization,
	override val inviterName: String,
	override val inviteeMailAddress: MailAddress,
) : Invitation {
	override val roles: MutableList<Role> = mutableListOf()
}
