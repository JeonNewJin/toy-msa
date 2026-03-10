package com.uno.event

import com.uno.dataserializer.DataSerializer

class Event<T : EventPayload>(
    val eventId: String,
    val type: EventType,
    val payload: T,
) {
    fun toJson(): String = DataSerializer.serialize(this)

    companion object {
        fun of(eventId: String, type: EventType, payload: EventPayload): Event<EventPayload> =
            Event(eventId, type, payload)

        fun fromJson(json: String): Event<EventPayload> {
            val eventRaw = DataSerializer.deserialize(json, EventRaw::class.java)
            val type = EventType.from(eventRaw.type)
            val payload = DataSerializer.deserialize(eventRaw.payload, type.payloadClass)
            return Event(eventRaw.eventId, type, payload)
        }
    }

    data class EventRaw(
        val eventId: String,
        val type: String,
        val payload: Any,
    )
}
