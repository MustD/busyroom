package com.example.room

import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.serialization.Jackson.map2json
import com.example.utils.RoomTestUtils
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class RoomResourceTest {

    private val storage = RoomStorage

    @Test
    fun testGetAllRooms() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        storage.storeRoom(RoomTestUtils.generateRoom())

        client.get("/room").apply {

            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun testGetSpecificRoom() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        val givenRoom = RoomTestUtils.generateRoom()
        storage.storeRoom(givenRoom)

        client.get("/room/${givenRoom.id}").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun testGetRoomNotFound() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }

        client.get("/room/${UUID.randomUUID()}").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
    }

    @Test
    fun testCreateRoom() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }


        val givenRoom = RoomTestUtils.generateRoom()

        client.post("/room") {
            contentType(ContentType.Application.Json)
            setBody(givenRoom.map2json())
        }.apply {
            assertEquals(HttpStatusCode.Created, status)
        }
    }

}