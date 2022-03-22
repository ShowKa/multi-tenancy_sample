package com.showka.multitenant_sample.system.authentification

import com.showka.multitenant_sample.system.value.MailAddress

/**
 * 認証済みのユーザー情報（Tokenから取得可能なもの）.
 */
interface Claim {
	val subject: String
	val mailAddress: MailAddress
}
