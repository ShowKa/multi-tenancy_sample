package com.showka.multitenant_sample.system.exception

/**
 * システム例外.
 *
 * 再起不可能例外。
 * 概ねプログラムのバグと判断できる場合にThrowする。
 *
 */
open class SystemException(
	/**
	 * メッセージ.
	 */
	override var message: String,
) : RuntimeException() {

	/**
	 * 原因
	 */
	override var cause: Throwable? = null

	constructor(message: String, cause: Throwable) : this(message) {
		cause.also { this.cause = it }
	}

}
