package com.showka.multitenant_sample.system.authentification

import com.showka.multitenant_sample.system.value.MailAddress

interface Member {
	val id: String // = user id
	val name: String
	val mailAddress: MailAddress
}
