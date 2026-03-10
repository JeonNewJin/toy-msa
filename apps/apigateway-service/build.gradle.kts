dependencies {
    // add-ons
    implementation(project(":supports:logging"))
    implementation(project(":supports:monitoring"))

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//    implementation("org.springframework.cloud:spring-cloud-starter-config")
//    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
//    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // Netty
    runtimeOnly("io.netty:netty-all")
}
