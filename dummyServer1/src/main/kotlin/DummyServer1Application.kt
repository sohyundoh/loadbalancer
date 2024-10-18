package com.loadbalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class DummyServer1Application

fun main(args : Array<String>) {
    runApplication<DummyServer1Application>(*args)
}