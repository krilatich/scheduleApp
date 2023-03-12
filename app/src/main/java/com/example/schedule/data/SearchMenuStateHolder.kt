package com.example.schedule.data

import androidx.compose.runtime.*

class SearchMenuStateHolder<T>() {

    var value by mutableStateOf("")

    var items = listOf<T>()

}

@Composable
fun <T>rememberSearchMenuStateHolder() = remember {
    SearchMenuStateHolder<T>()
}