package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.tenant.TenantContext
import com.showka.multitenant_sample.system.tenant.TenantID
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tenants")
class TenantController {

	/**
	 * get login user's tenant
	 */
	@GetMapping("/current")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	fun get(): Response? {
		val id = TenantContext.get()
		return Response(id)
	}

	companion object {
		class Response(tenantId: TenantID) {
			val id = tenantId.value
		}
	}
}
