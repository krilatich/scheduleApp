package com.example.schedule.data

@kotlinx.serialization.Serializable
data class TeachersResponse(
    val pageInfo: PageInfo,
    val teachers: List<Teacher>
)