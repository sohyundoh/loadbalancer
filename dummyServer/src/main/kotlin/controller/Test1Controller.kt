package com.loadbalancer.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class Test1Controller {

    @Value("\${spring.application.name}")
    lateinit var serverName: String

    @GetMapping("/test")
    fun test(): String {
        return serverName + "test"
    }
}