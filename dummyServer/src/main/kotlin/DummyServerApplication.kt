package com.loadbalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DummyServerApplication

fun main(args: Array<String>) {
    runApplication<DummyServerApplication>(*args)
}
