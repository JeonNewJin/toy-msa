package com.uno.interfaces.api

import com.uno.domain.CatalogService
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    private val env: Environment,
    private val catalogService: CatalogService,
) {
    @GetMapping("/health-check")
    fun status(): String =
        String.format(
            "It's Working in Catalog Service" +
                    ", port(local.server.port)=" + env.getProperty("local.server.port") +
                    ", port(server.port)=" + env.getProperty("server.port"),
        )

    @GetMapping("/catalogs")
    fun getCatalogs(): ResponseEntity<List<CatalogResponse>> =
        catalogService.findAll()
            .map { CatalogResponse.from(it) }
            .let { ResponseEntity.ok(it) }
}
