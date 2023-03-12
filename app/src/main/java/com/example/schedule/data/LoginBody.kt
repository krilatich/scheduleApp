package com.example.schedule.data

@kotlinx.serialization.Serializable
data class LoginBody(
    val email: String,
    val password: String
)