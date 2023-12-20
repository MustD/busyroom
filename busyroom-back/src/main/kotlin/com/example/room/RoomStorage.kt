package com.example.room

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * The `RoomStorage` object provides functionality for storing and retrieving `Room` objects.
 */
object RoomStorage {
    private val rooms = ConcurrentHashMap<UUID, Room>()

    /**
     * Stores a room in the system.
     *
     * This method accepts a Room object as a parameter and stores it in the system's storage. The Room object is associated with its ID.
     * If a room with the same ID already exists in the system, it will be replaced.
     *
     * @param room The Room object to store.
     */
    fun storeRoom(room: Room) {
        rooms[room.id] = room
    }

    /**
     * Retrieves a room by its ID.
     *
     * This method accepts a UUID as a parameter and returns the corresponding Room object, if it exists in the system.
     *
     * @param id The ID of the room to retrieve.
     * @return The Room object representing the retrieved room, or null if the room does not exist.
     */
    fun retrieveRoom(id: UUID): Room? {
        return rooms[id]
    }

    /**
     * Retrieves all the available rooms.
     *
     * This method returns a list of [Room] objects that represent all the rooms stored in the system.
     *
     * @return A list of [Room] objects representing all the available rooms.
     */
    fun retrieveAllRooms(): List<Room> {
        return rooms.values.toList()
    }

    /**
     * Retrieves a room by its name.
     *
     * This method accepts a name as a parameter and returns the corresponding Room object, if it exists in the system.
     *
     * @param name The name of the room to retrieve.
     * @return The Room object representing the retrieved room, or null if the room does not exist.
     */
    fun retrieveRoomByName(name: String): Room? {
        return rooms.values.find { it.name == name }
    }

    /**
     * Retrieves a list of rooms on a specific floor.
     *
     * This method accepts an integer representing the target floor and returns a list of Room objects that are located on that floor.
     *
     * @param targetFloor The target floor to retrieve rooms from.
     * @return A list of Room objects representing the rooms on the target floor.
     */
    fun retrieveRoomsByFloor(targetFloor: Int): List<Room> {
        return rooms.values.filter { it.floor == targetFloor }
    }

}