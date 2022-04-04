package com.showka.multitenant_sample.system.auth

import com.showka.multitenant_sample.system.value.MailAddress

interface PasswordService {
	fun requestChangePassword(mailAddress: MailAddress)
}
