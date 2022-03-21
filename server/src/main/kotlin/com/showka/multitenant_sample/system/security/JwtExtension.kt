package com.showka.multitenant_sample.system.security

import com.showka.multitenant_sample.system.value.MailAddress
import org.springframework.security.oauth2.jwt.Jwt

private const val namespace = "http://multitenant_sample.showka.com"

fun Jwt.getMailAddress(): MailAddress = MailAddress(this.getClaim<String>("${namespace}/email").toString())
