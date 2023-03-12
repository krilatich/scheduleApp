package com.example.schedule.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.EntryField
import com.example.schedule.SearchMenu
import com.example.schedule.data.*
import com.example.schedule.network.AuthRepository
import com.example.schedule.network.Network
import com.example.schedule.network.UserRepository
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileScreen(navController: NavController) {

    var isUpdated by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var loginInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf(Network.password.toString()) }
    var userNameInput by remember { mutableStateOf("") }
    val stateHolder = rememberSearchMenuStateHolder<Group>()

    if (!isUpdated) coroutineScope.launch {
        val profile = UserRepository().getProfile()
        profile?.let {
            userNameInput = it.userName
            loginInput = it.email
            stateHolder.value = (it.group ?: 0).toString()
        }
        isUpdated = true
    }

    val focusManager = LocalFocusManager.current
    var errors by remember {
        mutableStateOf(
            ErrorBody(
                status = false, message = "", errors = Errors(listOf(), listOf(), listOf())
            )
        )
    }
    val openDialog = remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Schedule",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.clickable(onClick = { navController.navigate("main_screen") })
        )
        Spacer(Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .width(360.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .border(BorderStroke(width = 1.dp, Blue200), shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Profile", style = MaterialTheme.typography.h2, modifier = Modifier
                )
            }
            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = userNameInput,
                onValueChange = { userNameInput = it },
                label = "Username",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )


            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = loginInput,
                onValueChange = { loginInput = it },
                label = "Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )

            SearchMenu(stateHolder)

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (passwordInput.isNotBlank()) {
                            val result = UserRepository().putProfile(
                                Profile(
                                    email = loginInput,
                                    password = passwordInput,
                                    group = if (stateHolder.value.isBlank()) 0
                                    else stateHolder.value.toInt(),
                                    userName = userNameInput
                                )
                            )
                            if (result != null) {
                                errors = result
                                openDialog.value = true
                            }
                            else Network.password = passwordInput
                        }
                    }
                },
                Modifier
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    "Confirm", style = MaterialTheme.typography.body1, color = Color.White
                )
            }
        }


    }

    if (openDialog.value) AlertDialog(onDismissRequest = {
        openDialog.value = false
    }, title = { Text(text = "Ошибки") }, text = {
        Column() {
            Text(text = errors.message)
        }
    }, buttons = {
        Button(onClick = { openDialog.value = false }) {
            Text("OK", style = MaterialTheme.typography.h1)
        }
    })
}

@Preview
@Composable
fun Prev090() {
    ScheduleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ProfileScreen(navController = rememberNavController())
        }
    }
}