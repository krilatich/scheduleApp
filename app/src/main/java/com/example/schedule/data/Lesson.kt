package com.example.schedule.data

data class Lesson(
    val number: Int,
    val subjectName: String = "subject_name",
    val classroomNumber: String = "219",
    val typeOfClassroom: String = "type",
    val groupNumber: String = "972101",
    val subgroup: String = "(1)",
    val teacher: String = "Горшков Семен Семенович"
)