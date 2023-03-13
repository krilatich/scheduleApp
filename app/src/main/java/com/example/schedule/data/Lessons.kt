package com.example.schedule.data

class Lessons (
    val mo:MutableList<LessonResponse> = mutableListOf(),
    val tu:MutableList<LessonResponse> = mutableListOf(),
    val th:MutableList<LessonResponse> = mutableListOf(),
    val we:MutableList<LessonResponse> = mutableListOf(),
    val fr:MutableList<LessonResponse> = mutableListOf(),
    val sa:MutableList<LessonResponse> = mutableListOf(),
    val su:MutableList<LessonResponse> = mutableListOf(),
    )