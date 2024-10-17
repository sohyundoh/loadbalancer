package com.loadbalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoadBalanceApplication

fun main(args: Array<String>) {
    runApplication<LoadBalanceApplication>(*args)
}