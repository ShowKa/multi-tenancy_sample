package com.showka.multitenant_sample.framework.authentification

import com.showka.multitenant_sample.system.authentification.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/organizations")
class OrganizationController {

	@Autowired
	private lateinit var service: OrganizationService

	/**
	 * get login user's organization
	 */
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun getAll(@AuthenticationPrincipal token: Jwt): List<Response> {
		val organizations = service.get(token.subject)
		return organizations.map {
			Response(it.id, it.identifierName, it.displayName)
		}
	}

	companion object {
		class Response(
			val id: String,
			val identifierName: String,
			val displayName: String
		)
	}
}
