package com.loadbalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DummyServer1Application

fun main(args : Array<String>) {
    runApplication<DummyServer1Application>(*args)
}