package com.loadbalancer.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class TestDummyController {

    @Value("\${server.port}")
    lateinit var serverPort: String

    @GetMapping("/test/{id}")
    fun test(@PathVariable id : Long): String {
        return "$serverPort 서버의 응답 => id : $id"
    }
}