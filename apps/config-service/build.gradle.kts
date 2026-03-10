dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")
}
