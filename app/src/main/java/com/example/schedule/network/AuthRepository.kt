package com.example.schedule.network

import android.util.Log
import com.example.schedule.data.ErrorBody
import com.example.schedule.data.Errors
import com.example.schedule.data.LoginBody
import com.example.schedule.data.RegisterBody
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AuthRepository {

    private val api = Network.getAuthApi()

    suspend fun register(body: RegisterBody): ErrorBody? {

        return try {
            val response = api.register(body)
            if (response.isSuccessful) {
                Network.token = response.body()!!.token
                null
            } else {
                val gson = Gson()

                return gson.fromJson(
                    response.errorBody()!!.charStream(),
                    ErrorBody::class.java
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

    suspend fun login(body: LoginBody): ErrorBody? {

        return try {
            val response = api.login(body)
            if (response.isSuccessful) {
                Network.token = response.body()!!.token
                null
            } else {
                Log.d("ers",response.raw().toString())
                ErrorBody(
                    status = false,
                    message = "Неправильный логин или пароль",
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

    suspend fun logout(): ErrorBody? {
        return try {
            val response = api.logout()
            null
        } catch (e: Exception) {
            ErrorBody(
                status = false,
                message = "500",
                errors = Errors(listOf(), listOf(), listOf())
            )
        }
    }


}