package com.example.schedule.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme

@Composable
fun SignUpScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Schedule", style = MaterialTheme.typography.h1,
            modifier = Modifier.clickable(
                onClick = { navController.navigate("main_screen") })
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
            val systemRole = "student"
            var retypePasswordInput by remember { mutableStateOf("") }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Registration", style = MaterialTheme.typography.h2,
                    modifier = Modifier
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
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )


            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = loginInput,
                onValueChange = { loginInput = it },
                label = "Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {focusManager.moveFocus(FocusDirection.Down)}
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            EntryField(
                modifier = Modifier.fillMaxWidth(),
                value = retypePasswordInput,
                onValueChange = { retypePasswordInput = it },
                label = "Retype password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {},
                Modifier
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    "Confirm",
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Already have an account?", style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
        Text(
            "Login",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .clickable(onClick = {
                    navController.navigate("signIn_screen")
                })
        )

    }

}


@Preview
@Composable
fun Pr9() {
    ScheduleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SignUpScreen(navController = rememberNavController())
        }
    }
}