package com.example.schedule.network

import com.example.schedule.data.ErrorBody
import com.example.schedule.data.Errors
import com.example.vasilyev.network.AuthApi
import com.example.vasilyev.network.MenuApi
import com.example.vasilyev.network.ScheduleApi
import com.example.vasilyev.network.UserApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Retrofit

object Network {
    private const val BASE_URL = "http://d.wolf.16.fvds.ru/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }



    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory( json.asConverterFactory("application/json".toMediaType()) )
            .build()


        return retrofit
    }

    private val retrofit = getRetrofit()

    var token:String? = null

    var password:String? = null

    var groupId = ""

    fun getAuthApi():AuthApi = retrofit.create(AuthApi::class.java)

    fun getScheduleApi():ScheduleApi = retrofit.create(ScheduleApi::class.java)

    fun getMenuApi():MenuApi = retrofit.create(MenuApi::class.java)

    fun getUserApi():UserApi = retrofit.create(UserApi::class.java)



}