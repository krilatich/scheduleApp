package com.example.schedule.data

@kotlinx.serialization.Serializable
data class GroupsResponse(
    val groups: List<Group>,
    val pageInfo: PageInfo
)