package com.example.room

import java.util.*

data class Room(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val floor: Int = 0,
)
