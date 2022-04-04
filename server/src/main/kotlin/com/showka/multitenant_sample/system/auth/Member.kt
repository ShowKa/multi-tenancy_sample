package com.showka.multitenant_sample.system.auth

import com.showka.multitenant_sample.system.value.MailAddress

interface Member {
	val id: String // = user id
	val name: String
	val mailAddress: MailAddress
}
