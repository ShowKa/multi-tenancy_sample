package com.showka.multitenant_sample.app

import com.showka.multitenant_sample.system.auth.Role
import com.showka.multitenant_sample.system.auth.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

enum class UserRole(private val roleName: String) : Role {
	Administrator("Administrator"),
	Operator("Operator"),
	;

	override fun getId(): String {
		return this.roleService.getId(this.getName())
	}

	override fun getName(): String {
		return this.roleName
	}

	// private
	private lateinit var roleService: RoleService
	private fun setService(service: RoleService) {
		roleService = service
	}

	// static
	companion object {

		@Component
		class Injector {

			@Autowired
			private lateinit var roleService: RoleService

			@PostConstruct
			fun postConstruct() {
				values().forEach {
					it.setService(roleService)
				}
			}
		}
	}
}
