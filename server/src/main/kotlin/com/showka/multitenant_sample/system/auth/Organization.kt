package com.showka.multitenant_sample.system.auth

interface Organization {
	val id: String
	val identifierName: String
	val displayName: String
}
