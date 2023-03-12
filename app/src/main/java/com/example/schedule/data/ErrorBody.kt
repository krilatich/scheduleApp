package com.example.schedule.data

data class ErrorBody(
    val errors: Errors,
    val message: String,
    val status: Boolean
)