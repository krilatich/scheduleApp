package com.example.schedule.data

@kotlinx.serialization.Serializable
data class LessonResponse(
    val classroom_name: String,
    val date: String,
    val group_number: Int,
    val lesson_id: String,
    val subgroup: String?,
    val subject_name: String,
    val teacher: String,
    val timeslot_number: Int,
    val type_of_lesson: String
)