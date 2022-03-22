package com.showka.multitenant_sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultiTenantSampleApplication

fun main(args: Array<String>) {
	runApplication<MultiTenantSampleApplication>(*args)
}
