package com.loadbalancer.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerProvider(
    @Value("\${resilience4j.circuitbreaker.configs.default.failureRateThreshold}")
    var failureRateThreshold: Float,
    @Value("\${resilience4j.circuitbreaker.configs.default.slowCallRateThreshold}")
    var slowCallRateThreshold: Float,
    @Value("\${resilience4j.circuitbreaker.configs.default.waitDurationInOpenState}")
    var waitDurationInOpenState: Long,
    @Value("\${resilience4j.circuitbreaker.configs.default.minimumNumberOfCalls}")
    var minimumNumberOfCalls: Int,
    @Value("\${resilience4j.circuitbreaker.configs.default.slidingWindowSize}")
    var slidingWindowSize: Int,
    @Value("\${resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState}")
    var permittedNumberOfCallsInHalfOpenState: Int
) {
    companion object {
        const val TEST_CIRCUIT: String = "testCircuitBreaker"
    }

    @Bean
    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
        return CircuitBreakerRegistry.ofDefaults()
    }

    @Bean
    fun testCircuitBreaker(circuitBreakerRegistry: CircuitBreakerRegistry): CircuitBreaker {
        return circuitBreakerRegistry.circuitBreaker(
            TEST_CIRCUIT, CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .slowCallRateThreshold(slowCallRateThreshold)
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .minimumNumberOfCalls(minimumNumberOfCalls)
                .slidingWindowSize(slidingWindowSize)
                .build()
        )
    }
}