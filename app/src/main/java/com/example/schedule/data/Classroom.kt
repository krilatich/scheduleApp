package com.example.schedule.data

@kotlinx.serialization.Serializable
data class Classroom(
    val classroomName: String,
    val classroom_id: String
)