package com.example.serialization

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Jackson {
    val objectMapper = jacksonObjectMapper()
    fun <T> T.map2json(): String = objectMapper.writeValueAsString(this)
}