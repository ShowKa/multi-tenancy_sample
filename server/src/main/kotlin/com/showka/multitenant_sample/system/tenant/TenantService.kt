package com.showka.multitenant_sample.system.tenant

import com.showka.multitenant_sample.system.authentification.Claim

interface TenantService {

	/**
	 * Auth0 APIを介して、ユーザーにテナントを設定します。
	 * 設定後、token（RulesによりClaimを拡張したもの）から Tenant ID を取得できるようになります。
	 */
	fun save(userClaim: Claim, tenantId: TenantID)

	/**
	 * Auth0 APIを介して、ユーザーの Tenant ID を取得します。
	 */
	fun get(userClaim: Claim): TenantID?

}
