package com.example.vasilyev.network

import com.example.schedule.data.*
import retrofit2.Response
import retrofit2.http.*

interface MenuApi {
    @GET("api/groups")
    suspend fun groups(@Query("filter")filter:String): Response<GroupsResponse>

    @GET("api/teachers")
    suspend fun teachers(@Query("filter")filter:String): Response<TeachersResponse>

    @GET("api/classrooms")
    suspend fun classrooms(@Query("filter")filter:String): Response<ClassroomsResponse>


}