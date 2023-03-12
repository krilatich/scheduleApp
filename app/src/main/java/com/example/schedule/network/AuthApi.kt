package com.example.vasilyev.network

import com.example.schedule.data.LoginBody
import com.example.schedule.data.RegisterBody
import com.example.schedule.data.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("api/account/register")
    suspend fun register(@Body body:RegisterBody): Response<TokenResponse>

    @POST("api/account/login")
    suspend fun login(@Body body:LoginBody): Response<TokenResponse>

    @POST("api/account/logout")
    suspend fun logout(): Response<Unit>

}