package com.example.schedule.data

import androidx.compose.runtime.*

class SearchMenuStateHolder {

    var value by mutableStateOf("")

    var items = listOf<String>()

    fun updateItems(list:List<String>){

        items = list

    }

}

@Composable
fun rememberSearchMenuStateHolder() = remember {
    SearchMenuStateHolder()
}