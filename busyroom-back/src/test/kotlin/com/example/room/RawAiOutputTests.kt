package com.example.room

import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RawAiOutputTests {
    lateinit var room1: Room
    lateinit var room2: Room

    @BeforeTest
    fun setup() {
        room1 = Room(UUID.randomUUID(), "Room1", 1)
        room2 = Room(UUID.randomUUID(), "Room2", 2)

        RoomStorage.storeRoom(room1)
        RoomStorage.storeRoom(room2)
    }

    @Test
    fun testRetrieveRoomWithInvalidId() {
        val invalidId = UUID.randomUUID() // Assuming that this id is not in storage
        val retrievedRoom = RoomStorage.retrieveRoom(invalidId)
        assertNull(
            actual = retrievedRoom,
            message = "Room with id $invalidId should not exist"
        )
    }

    @Test
    fun testRetrieveRoomByNameWithInvalidName() {
        val invalidName = "InvalidRoomName" // Assuming that this name is not in storage
        val retrievedRoom = RoomStorage.retrieveRoomByName(invalidName)
        assertNull(
            actual = retrievedRoom,
            message = "Room with name $invalidName should not exist"
        )
    }

    @Test
    fun testRetrieveRoomsByFloorWithInvalidFloor() {
        val invalidFloorNumber = 3 // Assuming that this floor number is not in storage
        val retrievedRooms = RoomStorage.retrieveRoomsByFloor(invalidFloorNumber)
        assertTrue(
            actual = retrievedRooms.isEmpty(),
            message = "No rooms should be found at floor $invalidFloorNumber"
        )
    }
}