package com.showka.multitenant_sample.framework.authentification

import com.showka.multitenant_sample.system.authentification.Member
import com.showka.multitenant_sample.system.authentification.MemberService
import com.showka.multitenant_sample.system.authentification.auth0.getOrganizationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController {

	@Autowired
	private lateinit var service: MemberService

	/**
	 * get login user's organization
	 */
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun getAll(@AuthenticationPrincipal token: Jwt): List<Response> {
		val orgId = token.getOrganizationId()!!
		return service.getOf(orgId).map {
			Response(it)
		}
	}

	companion object {
		class Response(member: Member) {
			val id: String = member.id
			val name: String = member.name
			val mailAddress: String = member.mailAddress.string
		}
	}
}
