package com.example.schedule.data


@kotlinx.serialization.Serializable
data class ScheduleBody(
    val schedule: List<Schedule>,
    val weekInfo: List<WeekInfo>
)