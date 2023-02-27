package com.example.schedule.screens

import android.widget.Space
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.ui.theme.Blue200
import com.example.schedule.ui.theme.ScheduleTheme

@Composable
fun StartScreen(navController: NavController) {


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(200.dp))
        Text("Schedule", style = MaterialTheme.typography.h1,
            modifier = Modifier.clickable(onClick = { navController.navigate("main_screen") })
        )
        Spacer(Modifier.height(25.dp))

        ScheduleChooser()

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "Sign In",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .clickable(onClick = { navController.navigate("signIn_screen") })
        )
        Spacer(Modifier.height(20.dp))

    }
}


@Preview
@Composable
fun Pr() {
    ScheduleTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            StartScreen(navController = rememberNavController())
        }
    }
}

@Composable
fun ScheduleChooser() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .width(360.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .border(BorderStroke(width = 1.dp, Blue200), shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )


    ) {
        var chosenButton by remember { mutableStateOf(0) }

        if (chosenButton == 0) {


            Spacer(Modifier.height(15.dp))

            Text(
                "Choose a schedule!",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Spacer(Modifier.height(1.dp))

            Button(
                onClick = {
                    chosenButton = 1
                },
                Modifier
                    .width(230.dp)
                    .height(35.dp)
            ) {
                Text("Groups", style = MaterialTheme.typography.body1)
            }
            Button(
                onClick = {
                    chosenButton = 2
                },
                Modifier
                    .width(230.dp)
                    .height(35.dp)
            ) {
                Text("Teachers", style = MaterialTheme.typography.body1)
            }
            Button(
                onClick = {
                    chosenButton = 3
                },
                Modifier
                    .width(230.dp)
                    .height(35.dp)
            ) {
                Text("Classrooms", style = MaterialTheme.typography.body1)
            }

            Spacer(Modifier.height(32.dp))
        } else
            if (chosenButton == 1) {
                Spacer(Modifier.height(1.dp))

                var text by remember { mutableStateOf("") }

                Row(modifier = Modifier.align(Alignment.Start)) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "back",
                        Modifier
                            .clickable(onClick = { chosenButton = 0 })
                    )

                    Text(
                        "Groups",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                }

                OutlinedTextField(
                    value = text, onValueChange = { text = it },
                    Modifier
                        .border(BorderStroke(1.dp, Blue200), shape = RoundedCornerShape(10.dp))
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp)), singleLine = true
                )



                Button(
                    onClick = {},
                    Modifier
                        .width(100.dp)
                        .height(35.dp)
                ) {
                    Text(
                        "Show",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }
                Spacer(Modifier.height(10.dp))

            } else
                if (chosenButton == 2) {
                    Spacer(Modifier.height(1.dp))

                    var text by remember { mutableStateOf("") }

                    Row(modifier = Modifier.align(Alignment.Start)) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            Modifier
                                .clickable(onClick = { chosenButton = 0 })

                        )
                        Text(
                            "Teachers",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(start = 15.dp)
                        )
                    }

                    OutlinedTextField(
                        value = text, onValueChange = { text = it },
                        Modifier
                            .border(
                                BorderStroke(1.dp, Blue200),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp)), singleLine = true
                    )



                    Button(
                        onClick = {},
                        Modifier
                            .width(100.dp)
                            .height(35.dp)
                    ) {
                        Text(
                            "Show",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.height(10.dp))

                } else
                    if (chosenButton == 3) {
                        Spacer(Modifier.height(1.dp))

                        var text by remember { mutableStateOf("") }

                        Row(modifier = Modifier.align(Alignment.Start)) {
                            Icon(
                                Icons.Default.ArrowBack, contentDescription = "back",
                                Modifier
                                    .clickable(onClick = { chosenButton = 0 })

                            )
                            Text(
                                "Classrooms",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            )
                        }

                        OutlinedTextField(
                            value = text, onValueChange = { text = it },
                            Modifier
                                .border(
                                    BorderStroke(1.dp, Blue200),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .height(50.dp)
                                .clip(RoundedCornerShape(10.dp)), singleLine = true
                        )



                        Button(
                            onClick = {},
                            Modifier
                                .width(100.dp)
                                .height(35.dp)
                        ) {
                            Text(
                                "Show",
                                style = MaterialTheme.typography.body1,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))

                    }
    }

}