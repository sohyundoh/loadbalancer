package com.loadbalancer.config

import io.netty.channel.ChannelOption
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebClientConfig(
    @Value("\${server.connection-time-out.seconds}")
    val connectionTimeOut: Int,

    @Value("\${server.write-time-out.seconds}")
    val writeTimeOut: Long
) {


    @LoadBalanced
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient
            .builder()
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().responseTimeout(java.time.Duration.ofSeconds(writeTimeOut))
                        .option(
                            ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeOut * 1000
                        )
                )
            )
    }
}