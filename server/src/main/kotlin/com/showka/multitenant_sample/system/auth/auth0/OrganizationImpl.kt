package com.showka.multitenant_sample.system.auth.auth0

import com.showka.multitenant_sample.system.auth.Organization
import com.auth0.json.mgmt.organizations.Organization as Auth0_Org

class OrganizationImpl(val org: Auth0_Org) : Organization {
	override val id: String = org.id
	override val identifierName: String = org.name
	override val displayName: String = org.displayName
}
