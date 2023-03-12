package com.example.schedule.data


@kotlinx.serialization.Serializable
data class PageInfo(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int
)