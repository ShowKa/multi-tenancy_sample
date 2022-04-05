package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.auth.PermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/permissions")
class PermissionController {

	@Autowired
	private lateinit var service: PermissionService

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun get(): Response {
		val response = Response()
		UserRole.values().forEach { role ->
			val permissions = service.get(role)
			response[role.name] = permissions
		}
		return response
	}

	companion object {
		class Response : HashMap<String, List<String>>()
	}
}
