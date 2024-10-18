package com.loadbalancer.controller

import io.netty.handler.timeout.ReadTimeoutException
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient


@RestController
class TestController(
    private val webClientBuilder: WebClient.Builder
) {

    @GetMapping("/test/{id}")
    @Cacheable(value = ["testCache"], key = "#id")
    fun useService(@PathVariable id: Long): String {
        val result = runCatching {
            webClientBuilder.build()
                .get()
                .uri("http://dummy/test/$id")
                .retrieve()
                .bodyToMono(String::class.java)
                .block()
        }.getOrElse {
            if (it.cause is ReadTimeoutException) {
                return "Try Again"
            } else {
                return "Exception Occurs"
            }
        }

        return result!!
    }
}