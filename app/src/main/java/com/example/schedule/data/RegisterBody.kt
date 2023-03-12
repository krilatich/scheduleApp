package com.example.schedule.data

@kotlinx.serialization.Serializable
data class RegisterBody(
    val email: String,
    val group: Int,
    val password: String,
    val userName: String
)