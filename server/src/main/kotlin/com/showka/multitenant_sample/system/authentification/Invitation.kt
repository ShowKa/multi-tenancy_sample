package com.showka.multitenant_sample.system.authentification

import com.showka.multitenant_sample.system.value.MailAddress

interface Invitation {
	val clientId: String
	val organization: Organization
	val inviterName: String
	val inviteeMailAddress: MailAddress
	val roles: List<Role>?
}
