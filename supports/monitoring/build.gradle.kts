dependencies {
    // Monitoring
    api("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-zipkin")

    // Slack Appender
    implementation("com.github.maricn:logback-slack-appender:${project.properties["slackAppenderVersion"]}")
}
