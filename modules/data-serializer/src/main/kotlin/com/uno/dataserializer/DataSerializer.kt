package com.uno.dataserializer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory

object DataSerializer {
    private val log = LoggerFactory.getLogger(DataSerializer::class.java)

    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun <T> deserialize(data: String, clazz: Class<T>): T =
        try {
            objectMapper.readValue(data, clazz)
        } catch (e: JsonProcessingException) {
            log.error("[DataSerializer.deserialize] data={}, clazz={}", data, clazz, e)
            throw e
        }

    fun <T> deserialize(data: Any, clazz: Class<T>): T = objectMapper.convertValue(data, clazz)

    fun serialize(obj: Any): String =
        try {
            objectMapper.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            log.error("[DataSerializer.serialize] object={}", obj, e)
            throw e
        }
}
