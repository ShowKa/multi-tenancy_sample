package com.showka.multitenant_sample.framework.authentification

import com.showka.multitenant_sample.system.authentification.Organization
import com.showka.multitenant_sample.system.authentification.OrganizationService
import com.showka.multitenant_sample.system.authentification.auth0.getOrganizationId
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
		val organizations = service.getBelongsTo(token.subject)
		return organizations.map {
			Response(it)
		}
	}

	/**
	 * get organization
	 */
	@GetMapping("/mine")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun getMine(@AuthenticationPrincipal token: Jwt): Response {
		val orgId = token.getOrganizationId()!!
		val org = service.get(orgId)
		return Response(org)
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
		return Response(organization)
	}

	/**
	 * update organization name.
	 */
	@PatchMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun patch(@AuthenticationPrincipal token: Jwt, @RequestBody form: Form): Response {
		val orgId = token.getOrganizationId()!!
		val organization = service.updateDisplayName(orgId, form.displayName)
		return Response(organization)
	}

	companion object {
		class Form {
			lateinit var displayName: String
		}

		class Response(organization: Organization) {
			val id: String = organization.id
			val identifierName: String = organization.identifierName
			val displayName: String = organization.displayName
		}
	}
}
