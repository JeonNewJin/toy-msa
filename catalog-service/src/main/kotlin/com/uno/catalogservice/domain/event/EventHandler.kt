package com.uno.catalogservice.domain.event

import com.uno.event.Event
import com.uno.event.EventPayload

interface EventHandler {
    fun handle(event: Event<out EventPayload>)

    fun supports(event: Event<out EventPayload>): Boolean
}