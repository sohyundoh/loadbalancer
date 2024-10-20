package com.loadbalancer.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@RestController
class TestController(
    private val webClientBuilder: WebClient.Builder
) {

    private val log = KotlinLogging.logger {}

    @GetMapping("/test/{id}")
    @Cacheable(value = ["testCache"], key = "#id")
    @CircuitBreaker(name = "testCircuitBreaker", fallbackMethod = "testFallBack")
    fun useService(@PathVariable id: Long): Mono<String> {
        return webClientBuilder.build()
            .get()
            .uri("http://dummy/test/$id")
            .retrieve()
            .onStatus({ status -> status.is4xxClientError || status.is5xxServerError }) { response ->
                Mono.error(RuntimeException("Service call failed with status code: ${response.statusCode()}"))
            }
            .bodyToMono(String::class.java)
    }

    fun testFallBack(id: Long, e: Exception): Mono<String> {
        log.error { ">> ERROR : FALL BACK OCCUR -> ${e.message}" }
        return Mono.just("Error occurs, Try few minutes later")
    }
}