dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

extra["springCloudVersion"] = "2023.0.3"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}