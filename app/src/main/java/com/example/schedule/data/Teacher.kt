package com.example.schedule.data

@kotlinx.serialization.Serializable
data class Teacher(
    val teacher_id: String,
    val userName: String
)