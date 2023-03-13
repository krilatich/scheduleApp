package com.example.vasilyev.network

import com.example.schedule.data.LessonResponse
import com.example.schedule.data.ScheduleBody
import retrofit2.Response
import retrofit2.http.*

interface ScheduleApi {
    @GET("api/schedule/group/{id}")
    suspend fun scheduleByGroup(
        @Path("id") id: String,
        @Query("weekStart") weekStart: String,
        @Query("weekEnd") weekEnd: String,
        @Query("plain") plain: Boolean = true
    ): Response<List<LessonResponse>>

    @GET("api/schedule/teacher/{id}")
    suspend fun scheduleByTeacher(
        @Path("id") id: String,
        @Query("weekStart") weekStart: String,
        @Query("weekEnd") weekEnd: String,
        @Query("plain") plain: Boolean = true
    ): Response<List<LessonResponse>>

    @GET("api/schedule/classroom/{id}")
    suspend fun scheduleByClassroom(
        @Path("id") id: String,
        @Query("weekStart") weekStart: String,
        @Query("weekEnd") weekEnd: String,
        @Query("plain") plain: Boolean = true
    ): Response<List<LessonResponse>>


}