package com.showka.multitenant_sample.system.auth.auth0

import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.mgmt.organizations.EnabledConnection
import com.showka.multitenant_sample.system.auth.Organization
import com.showka.multitenant_sample.system.auth.OrganizationService
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
		return OrganizationImpl(created)
	}

	override fun get(organizationId: String): Organization {
		val organization = managementApi.organizations().get(organizationId).execute()
		return OrganizationImpl(organization)
	}

	override fun getBelongsTo(userId: String): List<Organization> {
		val organizations = managementApi.users().getOrganizations(userId, null).execute().items
		return organizations.map {
			OrganizationImpl(it)
		}
	}

	override fun getByName(identifiedName: String): Organization {
		val getOne = managementApi.organizations().getByName(identifiedName).execute()
		return OrganizationImpl(getOne)
	}

	override fun updateDisplayName(organizationId: String, newName: String): Organization {
		val got = managementApi.organizations().get(organizationId).execute()
		val updated = Auth0Org()
		updated.name = got.name
		updated.displayName = newName
		val org = managementApi.organizations().update(organizationId, updated).execute()
		return OrganizationImpl(org)
	}
}
