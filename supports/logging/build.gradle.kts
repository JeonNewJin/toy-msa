dependencies {
    // P6spy
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:2.0.0")

    // Monitoring
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")

    // Slack Appender
    implementation("com.github.maricn:logback-slack-appender:1.6.1")
}
