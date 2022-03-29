package com.showka.multitenant_sample.system.authentification

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.EnabledConnection
import com.auth0.json.mgmt.organizations.Members
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.auth0.json.mgmt.organizations.Organization as Auth0Org

@Service
class OrganizationServiceImpl : OrganizationService {

	@Autowired
	private lateinit var managementApi: ManagementAPI

	override fun create(identifierName: String, displayName: String): Organization {
		// create
		val newOne = Auth0Org()
		newOne.name = identifierName
		newOne.displayName = displayName
		val created = managementApi.organizations().create(newOne).execute()
		// add connection
		val connections = managementApi.connections().listAll(null).execute().items
		connections.forEach {
			val enabled = EnabledConnection(it.id)
			managementApi.organizations().addConnection(created.id, enabled).execute()
		}
		// return
		return Organization(created.id, created.name, created.displayName)
	}

	override fun get(userId: String): List<Organization> {
		val organizations = managementApi.users().getOrganizations(userId, null).execute().items
		return organizations.map {
			Organization(it.id, it.name, it.displayName)
		}
	}

	override fun addMember(organizationId: String, userId: String) {
		val list = listOf(userId)
		this.addMembers(organizationId, list)
	}

	override fun addMembers(organizationId: String, userIdList: List<String>) {
		managementApi.organizations().addMembers(organizationId, Members(userIdList)).execute()
	}

	override fun getByName(identifiedName: String): Organization {
		val getOne = managementApi.organizations().getByName(identifiedName).execute()
		return Organization(getOne.id, getOne.name, getOne.displayName)
	}

	override fun updateDisplayName(organizationId: String, newName: String): Organization {
		val got = managementApi.organizations().get(organizationId).execute()
		val updated = Auth0Org()
		updated.name = got.name
		updated.displayName = newName
		managementApi.organizations().update(organizationId, updated).execute()
		return Organization(organizationId, updated.name, updated.displayName)
	}
}
