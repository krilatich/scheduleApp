package com.example.schedule.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.schedule.Navigation
import com.example.schedule.data.Lesson
import com.example.schedule.ui.theme.Gray200
import com.example.schedule.ui.theme.ScheduleTheme

@Composable
fun LessonScreen(navController: NavController, lessonId: Int) {


    val lesson: Lesson = Lesson(number = 1, subjectName = "Алгоритмы и структуры данных")

    Column(Modifier.fillMaxSize()) {

        Row(
            Modifier
                .shadow(5.dp)
                .background(Color.White)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.padding(20.dp).width(200.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier
                        .clickable(
                            onClick = {
                                navController.navigate("main_screen")
                            }
                        ))
                    Text(
                        lesson.subjectName, style = MaterialTheme.typography.h1
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "Group:", style = MaterialTheme.typography.h3
                )
                Text(
                    "${lesson.groupNumber} ${lesson.subgroup}",
                    style = MaterialTheme.typography.h3,
                    color = Color.Gray
                )
            }

            Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.End) {
                Text(
                    "8:45", style = MaterialTheme.typography.h1
                )

                Text(
                    "10:20",
                    style = MaterialTheme.typography.h3,
                    color = Color.Gray
                )
            }
        }

        Column(Modifier.padding(20.dp)){
            Text(
                "Teacher",
                style = MaterialTheme.typography.h3,
            )
            Text(
                lesson.teacher,
                style = MaterialTheme.typography.h3,
                color = Gray200
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Classroom",
                style = MaterialTheme.typography.h3,
            )
            Text(
                lesson.classroomNumber,
                style = MaterialTheme.typography.h3,
                color = Gray200
            )

        }


    }
}


@Preview
@Composable
fun Pre() {
    ScheduleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LessonScreen(navController = rememberNavController(), 10)
        }
    }
}