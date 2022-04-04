package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.authentification.InvitationService
import com.showka.multitenant_sample.system.authentification.auth0.getOrganizationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invitations")
class InvitationController {

	@Autowired
	private lateinit var service: InvitationService

	/**
	 * invite new member.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('update:settings')")
	@ResponseBody
	fun invite(
		@AuthenticationPrincipal token: Jwt,
		@RequestBody form: Form
	): Response {
		val clientId = token.getClaimAsString("azp")
		val organizationId = token.getOrganizationId()!!
		val id = service.invite(clientId, organizationId, form.inviterName, form.inviteeMailAddress, UserRole.Operator)
		return Response(id)
	}

	companion object {
		class Form {
			lateinit var inviterName: String
			lateinit var inviteeMailAddress: String
		}

		class Response(val invitationId: String)
	}
}
