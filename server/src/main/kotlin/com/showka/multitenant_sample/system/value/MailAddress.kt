package com.showka.multitenant_sample.system.value

class MailAddress(private val mailAddress: String) {

	// init
	init {
		require(mailAddress.isNotBlank()) {
			"メールアドレスを入力してください"
		}
		require(mailAddress.length <= 320) {
			"メールアドレスは320文字以下で入力してください : $mailAddress"
		}
	}

	// public
	val string = this.mailAddress

	// override
	override fun equals(other: Any?): Boolean {
		if (other !is MailAddress) {
			return false
		}
		return this.mailAddress == other.mailAddress
	}

	override fun hashCode(): Int {
		return this.mailAddress.hashCode()
	}
}
