plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "boilerplate"

include("dummyServer")
include("loadBalancer")
include("dummyServer1")
include("eurekaServer")
