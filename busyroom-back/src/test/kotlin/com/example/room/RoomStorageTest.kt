package com.example.room

import com.example.utils.RoomTestUtils
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RoomStorageTest {

    private val storage = RoomStorage

    @Test
    fun storeRoom() {
        val givenRoom = RoomTestUtils.generateRoom()

        storage.storeRoom(givenRoom)

        val retrievedRoom = storage.retrieveRoom(givenRoom.id)

        assertEquals(givenRoom, retrievedRoom)
    }

    @Test
    fun retrieveRoom() {
        val givenRoom = RoomTestUtils.generateRoom()

        storage.storeRoom(givenRoom)

        val retrievedRoom = storage.retrieveRoom(givenRoom.id)

        assertEquals(givenRoom, retrievedRoom)
    }

    @Test
    fun retrieveAllRooms() {
        val numberOfRooms = 10
        val givenRooms = List(numberOfRooms) {
            RoomTestUtils.generateRoom().also { storage.storeRoom(it) }
        }

        val retrievedRooms = storage.retrieveAllRooms()

        givenRooms.forEach { givenRoom ->
            assertTrue(retrievedRooms.any { it.id == givenRoom.id })
        }
    }

    @Test
    fun retrieveRoomByName() {
        val givenRoom = RoomTestUtils.generateRoom()

        storage.storeRoom(givenRoom)

        val retrievedRoom = storage.retrieveRoomByName(givenRoom.name)

        assertEquals(givenRoom, retrievedRoom)
    }

    @Test
    fun retrieveRoomsByFloor() {
        val givenFloor = (Math.random() * 100).roundToInt()
        val givenRoomsOnFloor = listOf(
            RoomTestUtils.generateRoom(floor = givenFloor),
            RoomTestUtils.generateRoom(floor = givenFloor),
        )
        givenRoomsOnFloor.forEach { storage.storeRoom(it) }

        val retrievedRooms = storage.retrieveRoomsByFloor(givenFloor)

        assertEquals(givenRoomsOnFloor.size, retrievedRooms.size)
        givenRoomsOnFloor.forEach { givenRoom ->
            assertTrue(retrievedRooms.any { it.id == givenRoom.id })
        }
    }


}