package com.showka.multitenant_sample.system.auth.auth0

import com.showka.multitenant_sample.system.auth.User
import org.springframework.security.oauth2.jwt.Jwt

class AuthenticatedUser(token: Jwt) : User {

	override val id: String = token.subject

}
