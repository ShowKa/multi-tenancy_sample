package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.auth.Member
import com.showka.multitenant_sample.system.auth.MemberService
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
	@PreAuthorize("hasAuthority('read:settings')")
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
