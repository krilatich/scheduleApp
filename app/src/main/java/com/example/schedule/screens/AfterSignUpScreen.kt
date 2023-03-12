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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.EntryField
import com.example.schedule.SearchMenu
import com.example.schedule.data.Group
import com.example.schedule.data.Profile
import com.example.schedule.data.rememberSearchMenuStateHolder
import com.example.schedule.network.Network
import com.example.schedule.network.UserRepository
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme
import com.example.vasilyev.network.UserApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AfterSignUpScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(140.dp))
        Text("Schedule", style = MaterialTheme.typography.h1)

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
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Are you in a group?", style = MaterialTheme.typography.h2, modifier = Modifier
                )
            }
            val stateHolder = rememberSearchMenuStateHolder<Group>()
            SearchMenu(stateHolder)


            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {
                    if (stateHolder.value.isNotBlank()) {
                        coroutineScope.launch {

                            val api = UserRepository()
                            val profile = api.getProfile()

                            profile?.let {
                                UserRepository().putProfile(
                                    Profile(
                                        userName = it.userName,
                                        group = stateHolder.value.toInt(),
                                        password = Network.password.toString(),
                                        email = it.email
                                    )
                                )
                            }
                            navController.navigate("main_screen/group/${Network.groupId}")
                        }
                    }
                    else navController.navigate("main_screen/group/${Network.groupId}")
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
            "If you don't have a group, leave the field blank",
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )

    }
}

@Preview
@Composable
fun Prev99() {
    ScheduleTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            AfterSignUpScreen(navController = rememberNavController())
        }
    }
}