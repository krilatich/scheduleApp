package com.example.vasilyev.network

import com.example.schedule.data.*
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @GET("api/account/profile")
    suspend fun getProfile(@Header("Authorization") token:String): Response<ProfileResponse>

    @PUT("api/account/profile")
    suspend fun putProfile(@Header("Authorization") token:String,@Body body:Profile): Response<Profile>

}