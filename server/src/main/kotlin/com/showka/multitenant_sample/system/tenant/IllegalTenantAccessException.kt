package com.showka.multitenant_sample.system.tenant

import com.showka.multitenant_sample.system.exception.SystemException

class IllegalTenantAccessException() :
	SystemException("不正なテナントにアクセス") {
}
