package com.example.schedule.data


@kotlinx.serialization.Serializable
data class Errors(
    val email: List<String>?,
    var password: List<String>?,
    val userName: List<String>?
)