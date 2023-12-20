package com.example.utils

import com.example.room.Room
import java.util.*

object RoomTestUtils {

    fun generateRoom(
        id: UUID = UUID.randomUUID(),
        name: String = UUID.randomUUID().toString(),
        floor: Int = 0,
    ) = Room(
        id = id, name = name, floor = floor
    )
}