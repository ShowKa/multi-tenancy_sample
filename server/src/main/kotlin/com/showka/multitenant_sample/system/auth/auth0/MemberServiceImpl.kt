package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.Members
import com.auth0.json.mgmt.organizations.Roles
import com.showka.multitenant_sample.system.auth.Member
import com.showka.multitenant_sample.system.auth.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl : MemberService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun getOf(organizationId: String): List<Member> {
		val members = managementApi.organizations().getMembers(organizationId, null).execute().items
		return members.map { OrganizationMember(it) }
	}

	override fun add(organizationId: String, userIdList: List<String>) {
		managementApi.organizations().addMembers(organizationId, Members(userIdList)).execute()
	}

	override fun assign(organizationId: String, userId: String, roleId: String) {
		managementApi.organizations().addRoles(organizationId, userId, Roles(listOf(roleId))).execute()
	}
}
