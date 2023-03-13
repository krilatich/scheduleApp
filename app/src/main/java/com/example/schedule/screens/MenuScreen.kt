package com.example.schedule.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.network.AuthRepository
import com.example.schedule.network.Network
import com.example.schedule.ui.theme.ScheduleTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, bottom = 40.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier
                    .clickable(
                        onClick = {
                            navController.navigate("main_screen/group/${Network.groupId}")
                        }
                    )
            )

            if(Network.email!=null)
            Text(
                Network.email.toString(), style = MaterialTheme.typography.h2,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate("profile_screen")
                })
            )

        }
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            "Menu", style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(20.dp))
        ScheduleChooser(navController)
        Spacer(Modifier.weight(1f))

        if(Network.email!=null)
        Text(
            "Log out",
            color = Color.Red,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .clickable(onClick = {
                    coroutineScope.launch{
                        AuthRepository().logout()
                    }
                    navController.navigate("start_screen") })
        )
        else
            Text(
                "Sign In",
                color = MaterialTheme.colors.primary,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate("signIn_screen") })
            )

    }


}


@Preview
@Composable
fun Prev2() {
    ScheduleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MenuScreen(navController = rememberNavController())
        }
    }
}