package com.example.schedule.data


sealed class Screen(val route:String){
    object StartScreen:Screen("start_screen")
    object MainScreen:Screen("main_screen")
    object SignInScreen:Screen("signIn_screen")
    object LessonScreen:Screen("lesson_screen")
}
