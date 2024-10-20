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
            .onStatus({ status -> status.is4xxClientError || status.is5xxServerError }) { response ->
                Mono.error(RuntimeException("Service call failed with status code: ${response.statusCode()}"))
            }
            .bodyToMono(String::class.java)
            .doOnError { e -> log.error { "Exception during WebClient call: ${e.message}" } }
    }

    fun testFallBack(id: Long, e: Exception): Mono<String> {
        log.error { ">> ERROR : FALL BACK OCCUR -> ${e.message}" }
        return Mono.just("Error occurs, Try few minutes later")
    }

}