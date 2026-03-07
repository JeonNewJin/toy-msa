dependencies {
    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // Netty
    runtimeOnly("io.netty:netty-all")
}