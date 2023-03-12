package com.example.schedule.data

@kotlinx.serialization.Serializable
data class ClassroomsResponse(
    val classrooms: List<Classroom>,
    val pageInfo: PageInfo
)