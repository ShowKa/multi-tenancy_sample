package com.showka.multitenant_sample.system.authentification

import com.showka.multitenant_sample.system.value.MailAddress

interface PasswordService {
	fun requestChangePassword(mailAddress: MailAddress)
}
