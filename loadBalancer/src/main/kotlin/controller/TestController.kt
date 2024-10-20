package com.loadbalancer.controller

import com.loadbalancer.adaptor.ApiCallModule
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class TestController(
    private val apiCallModule: ApiCallModule
) {

    @GetMapping("/test/{id}")
    fun useService(@PathVariable id: Long): Mono<String> {
        return apiCallModule.callModule(id)
    }

}