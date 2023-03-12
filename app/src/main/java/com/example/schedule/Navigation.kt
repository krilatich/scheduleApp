package com.example.schedule

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.schedule.data.Screen
import com.example.schedule.screens.*


@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }

        composable(Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screen.AfterSignUpScreen.route) {
            AfterSignUpScreen(navController = navController)
        }

        composable("${Screen.MainScreen.route}/{schedule_type}/{id}", arguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("schedule_type") { type = NavType.StringType }
        )
        ) {
            val id = it.arguments?.getString("id")!!
            val scheduleType = it.arguments?.getString("schedule_type")!!
            MainScreen(navController = navController, scheduleType = scheduleType, id = id)
        }

        composable(Screen.MenuScreen.route) {
            MenuScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable("${Screen.LessonScreen.route}/{lessonId}", arguments = listOf(
            navArgument("lessonId") { type = NavType.IntType }
        )) {
            val lessonId = it.arguments?.getInt("lessonId")!!
            LessonScreen(navController = navController, lessonId)
        }


    }


}