package com.example.schedule.network

import android.util.Log
import com.example.schedule.data.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MenuRepository {

    private val api = Network.getMenuApi()

    suspend fun getGroups(filter:String): List<Group>? {

        return try {
            val response = api.groups(filter)
            if (response.isSuccessful) {
                response.body()?.groups
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getClassrooms(filter:String): List<Classroom>? {

        return try {
            val response = api.classrooms(filter)
            if (response.isSuccessful) {
                response.body()?.classrooms
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getTeachers(filter:String): List<Teacher>? {

        return try {
            val response = api.teachers(filter)
            if (response.isSuccessful) {
                response.body()?.teachers
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }
    }


}