package com.loadbalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class DummyServerApplication

fun main(args: Array<String>) {
    runApplication<DummyServerApplication>(*args)
}
