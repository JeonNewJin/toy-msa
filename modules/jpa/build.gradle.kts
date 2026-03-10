plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
    id("java-test-fixtures")
}

dependencies {
    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // Querydsl
    api("com.querydsl:querydsl-jpa::jakarta")
    kapt("com.querydsl:querydsl-apt::jakarta")

    // MySQL
    runtimeOnly("com.mysql:mysql-connector-j")
    testRuntimeOnly("com.mysql:mysql-connector-j")

    // Test
    testImplementation("org.testcontainers:testcontainers-mysql")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testFixturesImplementation("org.testcontainers:testcontainers-mysql")
}
