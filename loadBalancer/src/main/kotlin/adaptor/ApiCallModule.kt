package com.loadbalancer.adaptor

import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ApiCallModule(
    private val webClientBuilder: WebClient.Builder
) {

    private val log = KotlinLogging.logger {}

    @CircuitBreaker(name = "testCircuitBreaker", fallbackMethod = "testFallBack")
    @Cacheable(value = ["testCache"], key = "#id")
    fun callModule(id: Long): Mono<String> {
        return webClientBuilder.build()
            .get()
            .uri("http://dummy/test/$id")
            .retrieve()
            .bodyToMono(String::class.java)
    }

    fun testFallBack(id: Long, e: Throwable): Mono<String> {
        log.error { ">> ERROR : FALL BACK OCCUR -> ${e.message}" }
        return Mono.just("Error occurs, Try few minutes later")
    }

}