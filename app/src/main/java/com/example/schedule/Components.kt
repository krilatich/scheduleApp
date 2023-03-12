package com.example.schedule

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.schedule.data.Classroom
import com.example.schedule.data.Group
import com.example.schedule.data.SearchMenuStateHolder
import com.example.schedule.data.Teacher
import com.example.schedule.network.MenuRepository
import com.example.schedule.network.Network
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
inline fun <reified T> SearchMenu(stateHolder: SearchMenuStateHolder<T>) {

    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    ) {
        EntryField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            value = stateHolder.value,
            onValueChange = {
                stateHolder.value = it
                coroutineScope.launch {
                    when (T::class) {

                        Group::class -> {
                            val groups = MenuRepository().getGroups(stateHolder.value)
                            if (groups != null)
                                stateHolder.items = groups as List<T>
                        }
                        Classroom::class -> {
                            val classrooms = MenuRepository().getClassrooms(stateHolder.value)
                            if (classrooms != null)
                                stateHolder.items = classrooms as List<T>
                        }
                        Teacher::class -> {
                            val teachers = MenuRepository().getTeachers(stateHolder.value)
                            if (teachers != null)
                                stateHolder.items = teachers as List<T>
                        }

                    }
                }
            },
        )
        val modifier =
            if (stateHolder.items.size > 3) Modifier.height(100.dp)
            else Modifier
        if (stateHolder.items.isNotEmpty())
            LazyColumn(
                modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(stateHolder.items.take(5)) { i ->
                    Row() {
                        Icon(Icons.Default.Search, contentDescription = "search")
                        when (i) {
                            is Group -> Text(
                                i.groupNumber.toString(), modifier = Modifier
                                    .clickable(onClick = {
                                        Network.groupId = i.group_id
                                        stateHolder.value = i.groupNumber.toString()
                                        stateHolder.items = listOf()
                                        focusManager.clearFocus()
                                    })
                                    .background(
                                        MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                            is Classroom -> Text(
                                i.classroomName, modifier = Modifier
                                    .clickable(onClick = {
                                        Network.groupId = i.classroom_id
                                        stateHolder.value = i.classroomName
                                        stateHolder.items = listOf()
                                        focusManager.clearFocus()
                                    })
                                    .background(
                                        MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                            is Teacher -> Text(
                                i.userName, modifier = Modifier
                                    .clickable(onClick = {
                                        Network.groupId = i.teacher_id
                                        stateHolder.value = i.userName
                                        stateHolder.items = listOf()
                                        focusManager.clearFocus()
                                    })
                                    .background(
                                        MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                        }
                    }
                }
            }

    }


}


@Composable
fun EntryField(
    modifier: Modifier = Modifier,
    label: String = "",
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier.height(60.dp),
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = Color.Gray,
                style = MaterialTheme.typography.body1
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colors.onBackground
        ),
        visualTransformation = visualTransformation
    )

}