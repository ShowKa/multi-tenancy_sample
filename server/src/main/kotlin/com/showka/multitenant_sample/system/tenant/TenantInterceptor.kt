package com.showka.multitenant_sample.system.tenant

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
			val tenantId = principal.getTenantId()
			if (tenantId != null) {
				TenantContext.set(tenantId)
			}
		}
		return true
	}

	override fun postHandle(
		request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?
	) {
		TenantContext.clear()
	}
}
