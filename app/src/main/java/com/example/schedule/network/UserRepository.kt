package com.example.schedule.network

import android.util.Log
import com.example.schedule.data.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class UserRepository {

    private val api = Network.getUserApi()

    suspend fun getProfile(): ProfileResponse? {

        return try {
            val response = api.getProfile("Bearer ${Network.token}")
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.d("erss", response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun putProfile(body: Profile): ErrorBody? {

        return try {
            val response = api.putProfile(token = "Bearer ${Network.token}", body = body)
            if (response.isSuccessful) {
                null
            } else {
                ErrorBody(
                    status = false,
                    message = response.raw().toString(),
                    errors = Errors(listOf(), listOf(), listOf())
                )
            }
        } catch (e: Exception) {
            ErrorBody(
                status = false,
                message = "500",
                errors = Errors(listOf(), listOf(), listOf())
            )
        }
    }

}