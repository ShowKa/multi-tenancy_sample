package com.showka.multitenant_sample.system.authentification

import com.auth0.client.auth.AuthAPI
import com.auth0.exception.APIException
import com.auth0.exception.Auth0Exception
import com.showka.multitenant_sample.system.exception.SystemException
import com.showka.multitenant_sample.system.value.MailAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PasswordServiceImpl : PasswordService {

	@Autowired
	private lateinit var auth: AuthAPI

	override fun requestChangePassword(mailAddress: MailAddress) {
		val address = mailAddress.string
		val request = auth.resetPassword(address, "Username-Password-Authentication")
		try {
			request.execute()
		} catch (e: APIException) {
			throw SystemException("fail password reset ($address). invalid request.", e)
		} catch (e: Auth0Exception) {
			throw SystemException("fail password reset ($address). unexpected error occur at auth0 server", e)
		}
	}
}
