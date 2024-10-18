package com.loadbalancer.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestDummyController {
    @Value("\${server.port}")
    lateinit var serverPort: String

    @GetMapping("/test")
    fun test(): String {
        return "$serverPort 에서 실행되고 있음 test"
    }
}