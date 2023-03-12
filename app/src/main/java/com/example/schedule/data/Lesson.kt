package com.example.schedule.data

data class Lesson(
    val number: Int,
    val subjectName: String = "subject_name",
    val classroomNumber: String = "219",
    val typeOfLesson: String = "type",
    val groupNumber: Int = 972101,
    val subgroup: String = "(1)",
    val teacher: String = "Горшков Семен Семенович"
)