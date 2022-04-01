package com.showka.multitenant_sample.system.authentification.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.showka.multitenant_sample.system.authentification.Member
import com.showka.multitenant_sample.system.authentification.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl : MemberService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun getOf(organizationId: String): List<Member> {
		val members = managementApi.organizations().getMembers(organizationId, null).execute().items
		return members.map { MemberImpl(it) }
	}
}
