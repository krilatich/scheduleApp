package com.example.schedule.data

@kotlinx.serialization.Serializable
data class WeekInfo(
    val currentWeek: Int,
    val endWeek: String,
    val startWeek: String
)