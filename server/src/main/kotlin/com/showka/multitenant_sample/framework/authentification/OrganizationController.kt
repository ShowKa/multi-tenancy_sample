package com.showka.multitenant_sample.framework.authentification

import com.showka.multitenant_sample.system.authentification.OrganizationService
import com.showka.multitenant_sample.system.value.ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

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

	/**
	 * register new organization.
	 */
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun register(@AuthenticationPrincipal token: Jwt, @RequestBody form: Form): Response {
		val id = ID()
		val orgId = "org" + id.value
		val organization = service.create(orgId, form.displayName)
		service.addMember(organization.id, token.subject)
		return Response(organization.id, organization.identifierName, organization.displayName)
	}

	companion object {
		class Form {
			lateinit var displayName: String
		}

		class Response(
			val id: String,
			val identifierName: String,
			val displayName: String
		)
	}
}
