package com.showka.multitenant_sample.system.exception

/**
 * アプリケーション例外.
 *
 * 業務続行可能例外。
 * プログラムのバグではなく、ユーザーの入力ミス等でThrowされる。
 *
 */
open class ApplicationException(
	/**
	 * メッセージ.
	 */
	override var message: String
) : RuntimeException() {

	/**
	 * メッセージ取得.
	 *
	 * @return メッセージ
	 */
	fun getMessageAsHtml(): String {
		return message.replace("\r\n|\n|\r".toRegex(), "<br>")
	}
}
