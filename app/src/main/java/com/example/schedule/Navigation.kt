package com.example.schedule

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.schedule.data.Screen
import com.example.schedule.screens.LessonScreen
import com.example.schedule.screens.MainScreen
import com.example.schedule.screens.SignInScreen
import com.example.schedule.screens.StartScreen


@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route){
        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

        composable(Screen.SignInScreen.route){
            SignInScreen(navController = navController)
        }

        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable("${Screen.LessonScreen.route}/{lessonId}", arguments = listOf(
            navArgument("lessonId"){type = NavType.IntType}
        )){
            val lessonId = it.arguments?.getInt("lessonId")!!
            LessonScreen(navController = navController,lessonId)
        }


    }



}