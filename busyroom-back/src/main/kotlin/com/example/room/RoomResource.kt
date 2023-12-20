package com.example.room

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

/**
 * Handles the routing for retrieving and storing room resources.
 *
 * The `roomResources` method defines the routes for accessing and manipulating room resources.
 * It supports the following routes:
 *  - GET*/
fun Routing.roomResources() {

    val storage = RoomStorage

    route("room") {

        get {
            val rooms = storage.retrieveAllRooms()
            call.respond(rooms)
        }



        get("{roomId}") {
            val roomId = runCatching {
                UUID.fromString(call.parameters["roomId"])
            }.getOrDefault(UUID(0, 0))

            val room = storage.retrieveRoom(roomId)
            if (room != null) {
                call.respond(room)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val room = call.receive<Room>()
            storage.storeRoom(room)
            call.respond(HttpStatusCode.Created)
        }
    }

}