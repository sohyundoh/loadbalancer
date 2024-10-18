package com.loadbalancer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier


class LoadBalanceConfig {

    @Value("\${instance.name}")
    lateinit var SERVER_NAME: String


}