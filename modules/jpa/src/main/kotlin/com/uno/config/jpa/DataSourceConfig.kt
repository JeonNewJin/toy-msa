package com.uno.config.jpa

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "datasource.mysql-jpa.main")
    fun mySqlMainHikariConfig(): HikariConfig = HikariConfig()

    @Primary
    @Bean
    fun mySqlMainDataSource(
        @Qualifier("mySqlMainHikariConfig") hikariConfig: HikariConfig,
    ) = HikariDataSource(hikariConfig)

    @Bean(initMethod = "migrate")
    @ConditionalOnProperty(prefix = "spring.flyway", name = ["enabled"], havingValue = "true", matchIfMissing = false)
    fun flyway(
        @Qualifier("mySqlMainDataSource") dataSource: DataSource,
    ): Flyway = Flyway.configure()
        .dataSource(dataSource)
        .locations("classpath:db/migration")
        .load()
}
