package com.showka.multitenant_sample.system.auth

interface Role {
	fun getId(): String
	fun getName(): String
}
