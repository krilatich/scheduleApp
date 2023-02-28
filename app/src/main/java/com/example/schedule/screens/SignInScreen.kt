package com.example.schedule.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.data.Screen
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme
import java.security.KeyStore.Entry
import kotlin.math.log

@Composable
fun SignInScreen(navController: NavController) {


    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(200.dp))
        Text(
            "Schedule", style = MaterialTheme.typography.h1,
            modifier = Modifier.clickable(
                onClick = { navController.navigate("main_screen") })
        )
        Spacer(Modifier.height(25.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .width(360.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .border(BorderStroke(width = 1.dp, Blue200), shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp)
        ) {

            var loginInput by remember { mutableStateOf("") }
            var passwordInput by remember {
                mutableStateOf("")
            }

            Spacer(Modifier.height(1.dp))


            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Authorization", style = MaterialTheme.typography.h2,
                    modifier = Modifier
                )
                Text(
                    "If you do not have an account, \n" +
                            "contact the university", style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }

            EntryField(
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
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
            )
            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {},
                Modifier
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    "Enter",
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
            Spacer(Modifier.height(10.dp))
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
    onValueChange: (String) -> Unit
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
        )
    )

}


@Preview
@Composable
fun Pr3() {
    ScheduleTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SignInScreen(navController = rememberNavController())
        }
    }
}