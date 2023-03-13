package com.example.schedule.network

import android.util.Log
import com.example.schedule.data.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.json.JSONObject
import java.util.Calendar


class ScheduleRepository {

    private val api = Network.getScheduleApi()

    suspend fun getScheduleGroup(weekStart: String, weekEnd: String, id: String): List<LessonResponse>? {

        return try {
            val response = api.scheduleByGroup(id = id, weekStart, weekEnd)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }

    }

    suspend fun getScheduleTeacher(weekStart: String, weekEnd: String, id: String): List<LessonResponse>? {

        return try {
            val response = api.scheduleByTeacher(id = id, weekStart, weekEnd)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }

    }

    suspend fun getScheduleClassroom(weekStart: String, weekEnd: String, id: String): List<LessonResponse>? {

        return try {
            val response = api.scheduleByClassroom(id = id, weekStart, weekEnd)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                Log.d("erss",response.raw().toString())
                null
            }
        } catch (e: Exception) {
            null
        }

    }


}