package com.showka.multitenant_sample.system.authentification.auth0

import com.showka.multitenant_sample.system.authentification.Member
import com.showka.multitenant_sample.system.value.MailAddress
import com.auth0.json.mgmt.organizations.Member as Auth0Member

class MemberImpl(member: Auth0Member) : Member {

	override val id: String
	override val name: String
	override val mailAddress: MailAddress

	init {
		this.id = member.userId
		this.name = member.name
		this.mailAddress = MailAddress(member.email)
	}

}
