package com.example.schedule.data

@kotlinx.serialization.Serializable
data class Schedule(
    val classroom: String,
    val date: String,
    val group: Int,
    val lesson: String,
    val lesson_id: String,
    val subgroup: String?,
    val teacher: String,
    val timeslot: Int,
    val type_of_lesson: String
)