package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.auth.MemberService
import com.showka.multitenant_sample.system.auth.Organization
import com.showka.multitenant_sample.system.auth.OrganizationService
import com.showka.multitenant_sample.system.auth.RoleAssignService
import com.showka.multitenant_sample.system.auth.auth0.AuthenticatedUser
import com.showka.multitenant_sample.system.auth.auth0.getOrganizationId
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
	private lateinit var organizationService: OrganizationService

	@Autowired
	private lateinit var memberService: MemberService

	@Autowired
	private lateinit var assignService: RoleAssignService

	/**
	 * get login user's organization
	 */
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun getAll(@AuthenticationPrincipal token: Jwt): List<Response> {
		val organizations = organizationService.getBelongsTo(token.subject)
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
		val org = organizationService.get(orgId)
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
		val organization = organizationService.create(orgId, form.displayName)
		val user = AuthenticatedUser(token)
		memberService.add(organization, user)
		assignService.assign(UserRole.Administrator, user, organization)
		return Response(organization)
	}

	/**
	 * update organization name.
	 */
	@PatchMapping
	@PreAuthorize("hasAuthority('update:settings')")
	@ResponseBody
	fun patch(@AuthenticationPrincipal token: Jwt, @RequestBody form: Form): Response {
		val orgId = token.getOrganizationId()!!
		val organization = organizationService.updateDisplayName(orgId, form.displayName)
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
