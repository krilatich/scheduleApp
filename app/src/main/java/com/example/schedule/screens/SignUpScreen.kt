package com.example.schedule.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.EntryField
import com.example.schedule.data.ErrorBody
import com.example.schedule.data.Errors
import com.example.schedule.data.RegisterBody
import com.example.schedule.network.AuthRepository
import com.example.schedule.network.Network
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current
    var errors: ErrorBody by remember {
        mutableStateOf(
            ErrorBody(
                status = false,
                message = "500",
                errors = Errors(listOf(), listOf(), listOf())
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

            var loginInput by remember { mutableStateOf("") }
            var passwordInput by remember { mutableStateOf("") }
            var userNameInput by remember { mutableStateOf("") }
            var retypePasswordInput by remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Registration", style = MaterialTheme.typography.h2, modifier = Modifier
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
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
                visualTransformation = PasswordVisualTransformation()
            )

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = retypePasswordInput,
                onValueChange = { retypePasswordInput = it },
                label = "Retype password",
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

                        if (passwordInput != retypePasswordInput) {
                            errors = ErrorBody(
                                status = false,
                                message = "",
                                errors = Errors(
                                    password = listOf("passwords do not match"),
                                    email = listOf(),
                                    userName = listOf()
                                )
                            )
                            openDialog.value = true
                        } else {
                            val result = AuthRepository().register(
                                RegisterBody(
                                    email = loginInput,
                                    password = passwordInput,
                                    group = 0,
                                    userName = userNameInput
                                )
                            )
                            if (result != null) {
                                errors = result
                                openDialog.value = true
                            } else {
                                Network.email = loginInput
                                Network.password = passwordInput
                                navController.navigate("after_signUp_screen")
                            }
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

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Already have an account?", style = MaterialTheme.typography.body1, color = Color.Gray
        )
        Text(
            "Login",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.clickable(onClick = {
                navController.navigate("signIn_screen")
            })
        )


    }

    if (openDialog.value) AlertDialog(onDismissRequest = {
        openDialog.value = false
    }, title = { Text(text = "Ошибки") }, text = {
        Column() {
            if (errors.errors.email != null) {
                Text(text = "Email", style = MaterialTheme.typography.h2)
                for (e in errors.errors.email!!) Text(e)
            }
            if (errors.errors.password != null) {
                Text(text = "Password", style = MaterialTheme.typography.h2)
                for (e in errors.errors.password!!) Text(e)
            }
            if (errors.errors.userName != null) {
                Text(text = "Username", style = MaterialTheme.typography.h2)
                for (e in errors.errors.userName!!) Text(e)
            }
        }
    }, buttons = {
        Button(onClick = { openDialog.value = false }) {
            Text("OK", style = MaterialTheme.typography.h1)
        }
    })

}


@Preview
@Composable
fun Pr9() {
    ScheduleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            SignUpScreen(navController = rememberNavController())
        }
    }
}