package com.example.schedule.screens

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
import com.example.schedule.data.LoginBody
import com.example.schedule.data.RegisterBody
import com.example.schedule.network.AuthRepository
import com.example.schedule.network.Network
import com.example.schedule.network.UserRepository
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun SignInScreen(navController: NavController) {


    val focusManager = LocalFocusManager.current
    val openDialog = remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(140.dp))
        Text(
            "Schedule",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.clickable(onClick = { navController.navigate("main_screen") })
        )
        Spacer(modifier = Modifier.height(20.dp))
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

            var loginInput by remember { mutableStateOf("") }
            var passwordInput by remember {
                mutableStateOf("")
            }
            val coroutineScope = rememberCoroutineScope()


            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Authorization", style = MaterialTheme.typography.h2, modifier = Modifier
                )

            }

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = loginInput,
                onValueChange = { loginInput = it },
                label = "Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )

            EntryField(modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                visualTransformation = PasswordVisualTransformation())
            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {

                    coroutineScope.launch {
                        val result = AuthRepository().login(
                            LoginBody(
                                email = loginInput,
                                password = passwordInput,
                            )
                        )
                        if (result != null) {
                            error = result.message
                            openDialog.value = true
                        } else {
                            Network.email = loginInput
                            Network.password = passwordInput
                            val profile = UserRepository().getProfile()
                            if (profile != null) navController.navigate("main_screen/group/${profile.group}")
                        }
                    }

                },
                Modifier
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    "Enter", style = MaterialTheme.typography.body1, color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Do not have an account?", style = MaterialTheme.typography.body1, color = Color.Gray
        )
        Text(
            "Register",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.clickable(onClick = {
                    navController.navigate("signUp_screen")
                })
        )


    }

    if (openDialog.value) AlertDialog(onDismissRequest = {
        openDialog.value = false
    }, title = { Text(text = "Ошибки") }, text = {
        Column() {
            Text(error, style = MaterialTheme.typography.h3)
        }
    }, buttons = {
        Button(onClick = { openDialog.value = false }) {
            Text("OK", style = MaterialTheme.typography.h1)
        }
    })


}


@Preview
@Composable
fun Pr3() {
    ScheduleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            SignInScreen(navController = rememberNavController())
        }
    }
}