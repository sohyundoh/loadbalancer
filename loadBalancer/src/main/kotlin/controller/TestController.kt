package com.loadbalancer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient


@RestController
class TestController {

    @Autowired
    lateinit var webClientBuilder: WebClient.Builder

    @GetMapping("/test")
    fun useService(): String {
        return webClientBuilder.build()
            .get()
            .uri("http://dummy/test")
            .retrieve()
            .bodyToMono(String::class.java)
            .block() ?: "No response"
    }
}