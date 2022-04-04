package com.showka.multitenant_sample.system.authentification

interface MemberService {
	fun getOf(organizationId: String): List<Member>
	fun getOf(organization: Organization): List<Member> {
		return getOf(organization.id)
	}
}
