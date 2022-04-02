package com.showka.multitenant_sample.system.tenant

import com.showka.multitenant_sample.system.authentification.auth0.getOrganizationId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TenantInterceptor : HandlerInterceptor {

	override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
		val context = SecurityContextHolder.getContext()
		val auth = context.authentication
		val principal = auth.principal
		if (principal is Jwt) {
			val orgId = principal.getOrganizationId()
			if (orgId != null) {
				val tenantId = TenantID.generateByOrgId(orgId)
				TenantContext.set(tenantId)
			} else {
				val empty = TenantID.generateEmpty()
				TenantContext.set(empty)
			}
		} else {
			val empty = TenantID.generateEmpty()
			TenantContext.set(empty)
		}
		return true
	}

	override fun postHandle(
		request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?
	) {
		TenantContext.clear()
	}
}
