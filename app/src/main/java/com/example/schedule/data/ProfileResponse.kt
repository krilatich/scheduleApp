package com.example.schedule.data

@kotlinx.serialization.Serializable
data class ProfileResponse(
    val email: String,
    val group: Int?,
    val userName: String
)