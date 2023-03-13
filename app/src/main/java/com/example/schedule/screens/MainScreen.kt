@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.schedule.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedule.data.Lesson
import com.example.schedule.data.LessonResponse
import com.example.schedule.data.Lessons
import com.example.schedule.network.Network
import com.example.schedule.network.ScheduleRepository
import com.example.schedule.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

var listLessons: List<LessonResponse>? = listOf()
var lessons = Lessons()

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavController, scheduleType: String, id: String) {

    val calendar: Calendar = Calendar.getInstance()

    Log.d("mth", calendar.get(Calendar.MONTH).toString())

    val months = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    )

    var isUpdated by remember { mutableStateOf(false) }

    val header = "Schedule for $scheduleType"

    val coroutineScope = rememberCoroutineScope()

    if (calendar.get(android.icu.util.Calendar.DAY_OF_WEEK) == 1) calendar.add(Calendar.DATE, 1)
    val pagerState = rememberPagerState(initialPage = calendar.get(Calendar.DAY_OF_WEEK) - 2)

    calendar.add(Calendar.DATE, pagerState.currentPage - calendar.get(Calendar.DAY_OF_WEEK) + 2)

    if (!isUpdated) {
        coroutineScope.launch {

            val calendarClone = calendar.clone() as Calendar
            calendarClone.add(Calendar.DATE, 6)

            when (scheduleType) {

                "group" -> {
                    listLessons = ScheduleRepository().getScheduleGroup(
                        weekStart = parseDate(calendar), weekEnd = parseDate(calendarClone), id = id
                    )
                }

                "teacher" -> {
                    listLessons = ScheduleRepository().getScheduleTeacher(
                        weekStart = parseDate(calendar), weekEnd = parseDate(calendarClone), id = id
                    )
                }
                else -> {
                    listLessons = ScheduleRepository().getScheduleClassroom(
                        weekStart = parseDate(calendar), weekEnd = parseDate(calendarClone), id = id
                    )
                }

            }

            if (listLessons == null) {
                listLessons = listOf()
            }

            calendarClone.add(Calendar.DATE, -6)

            for (l in listLessons!!) {
                if (l.date == parseDate(calendarClone)) lessons.mo.add(l)
                else {
                    calendarClone.add(Calendar.DATE, 1)
                    if (l.date == parseDate(calendarClone)) {
                        lessons.tu.add(l)
                        calendarClone.add(Calendar.DATE, -1)
                    } else {
                        calendarClone.add(Calendar.DATE, 1)
                        if (l.date == parseDate(calendarClone)) {
                            lessons.th.add(l)
                            calendarClone.add(Calendar.DATE, -2)
                        } else {
                            calendarClone.add(Calendar.DATE, 1)
                            if (l.date == parseDate(calendarClone)) {
                                lessons.fr.add(l)
                                calendarClone.add(Calendar.DATE, -3)
                            } else {
                                calendarClone.add(Calendar.DATE, 1)
                                if (l.date == parseDate(calendarClone)) {
                                    lessons.sa.add(l)
                                    calendarClone.add(Calendar.DATE, -4)
                                } else {
                                    calendarClone.add(Calendar.DATE, 1)
                                    if (l.date == parseDate(calendarClone)) {
                                        lessons.su.add(l)
                                        calendarClone.add(Calendar.DATE, -5)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    isUpdated = true
}

Scaffold(bottomBar = {
    NavigationBottomBar(
        navController = navController,
        mLocalContext = LocalContext.current,
        pagerState = pagerState,
        calendar = calendar
    )
}) {

    Column() {
        //Text(calendar.get(Calendar.DAY_OF_MONTH).toString())

        Column(
            Modifier
                .shadow(5.dp)
                .background(Color.White)
        ) {

            Column(
                Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            ) {
                Text(
                    header, style = MaterialTheme.typography.h2, modifier = Modifier

                )
                Text(
                    "${months[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.YEAR)}",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray

                )
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.clickable(onClick = {
                            calendar.add(Calendar.DATE, -7)
                            lessons = Lessons()
                            isUpdated = false
                            coroutineScope.launch {
                                pagerState.scrollToPage(1)
                                pagerState.scrollToPage(0)
                            }

                        })
                    )


                    val daysOfWeek = listOf(
                        "Mo", "Tu", "Th", "We", "Fr", "Sa"
                    )

                    for (i in 2..7) {

                        var modifier: Modifier = Modifier
                        if (pagerState.currentPage == i - 2) modifier = Modifier.border(
                            border = BorderStroke(1.dp, Blue200),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Column(
                            modifier = modifier
                                .padding(10.dp)
                                .clickable(onClick = {

                                    coroutineScope.launch {

                                        calendar.add(
                                            Calendar.DATE,
                                            i - calendar.get(Calendar.DAY_OF_WEEK)
                                        )
                                        pagerState.animateScrollToPage(i - 2)

                                    }

                                })
                        ) {

                            Text(
                                daysOfWeek[i - 2],
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier

                            )

                            val temp = i - calendar.get(Calendar.DAY_OF_WEEK)
                            calendar.add(Calendar.DATE, temp)

                            Text(
                                text = (calendar.get(Calendar.DAY_OF_MONTH)).toString(),
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )

                            calendar.add(Calendar.DATE, -temp)

                        }

                    }

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "forward",
                        modifier = Modifier.clickable(onClick = {
                            calendar.add(Calendar.DATE, 7)
                            lessons = Lessons()
                            isUpdated = false
                            coroutineScope.launch {
                                pagerState.scrollToPage(1)
                                pagerState.scrollToPage(0)
                            }
                        })
                    )

                }

                Spacer(Modifier.height(10.dp))
            }

        }


        HorizontalPager(
            count = 6,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.wrapContentHeight()
        ) { i ->

            Box(modifier = Modifier.fillMaxSize()) {
                when (i) {
                    0 -> LessonsList(navController, lessons.mo)
                    1 -> LessonsList(navController, lessons.tu)
                    2 -> LessonsList(navController, lessons.th)
                    3 -> LessonsList(navController, lessons.fr)
                    4 -> LessonsList(navController, lessons.sa)
                    5 -> LessonsList(navController, lessons.su)
                }
            }

        }
    }
}

}

@Composable
fun LessonsList(
    navController: NavController,
    listLessons: List<LessonResponse>
) {


    LazyColumn(
        Modifier.padding(start = 20.dp, end = 20.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {

        }
        for (l in listLessons)
            item {
                Lesson(
                    lesson = Lesson(
                        number = l.timeslot_number,
                        subjectName = l.subject_name,
                        classroomNumber = l.classroom_name,
                        typeOfLesson = l.type_of_lesson,
                        groupNumber = l.group_number,
                        subgroup = l.subgroup ?: "",
                        teacher = l.teacher
                    ), modifier = Modifier.clickable(onClick = {
                        Network.lesson = Lesson(
                            number = l.timeslot_number,
                            subjectName = l.subject_name,
                            classroomNumber = l.classroom_name,
                            typeOfLesson = l.type_of_lesson,
                            groupNumber = l.group_number,
                            subgroup = l.subgroup ?: "",
                            teacher = l.teacher
                        )
                        navController.navigate("lesson_screen")

                    })
                )
            }
        item {

            Spacer(Modifier.height(20.dp))
        }

    }


}


@Composable
fun Lesson(lesson: Lesson, modifier: Modifier = Modifier) {


    val lessonTime = mapOf(
        1 to Pair("8:45", "10:20"),
        2 to Pair("10:35", "12:10"),
        3 to Pair("12:25", "14:00"),
        4 to Pair("14:45", "16:20"),
        5 to Pair("16:35", "18:10"),
        6 to Pair("18:25", "20:00"),
        7 to Pair("20:15", "21:50"),
    )

    val lessonColor = when (lesson.number) {

        1 -> Pair(firstLessonColor_1, firstLessonColor_2)
        2 -> Pair(secondLessonColor_1, secondLessonColor_2)
        3 -> Pair(thirdLessonColor_1, thirdLessonColor_2)
        4 -> Pair(fourthLessonColor_1, fourthLessonColor_2)
        5 -> Pair(fifthLessonColor_1, fifthLessonColor_2)
        6 -> Pair(sixthLessonColor_1, sixthLessonColor_2)
        else -> Pair(seventhLessonColor_1, seventhLessonColor_2)

    }


    Row(Modifier.fillMaxWidth()) {


        Column(
            Modifier
                .size(100.dp)
                .background(lessonColor.first, shape = RoundedCornerShape(10.dp))
                .border(
                    BorderStroke(1.dp, lessonColor.second), shape = RoundedCornerShape(10.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                lessonTime[lesson.number]!!.first,
                style = MaterialTheme.typography.h1,
                color = Color.Black
            )
            Text(
                lessonTime[lesson.number]!!.second,
                style = MaterialTheme.typography.h2,
                color = Color.Gray
            )

        }
        Spacer(Modifier.width(20.dp))

        Column(
            modifier
                .fillMaxWidth()
                .height(100.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(BorderStroke(1.dp, Blue200), shape = RoundedCornerShape(10.dp))
                .padding(20.dp), verticalArrangement = Arrangement.Center
        ) {
            Text(lesson.subjectName, style = MaterialTheme.typography.h2, color = Gray200)

            Text(
                "${lesson.classroomNumber} ${lesson.typeOfLesson}\n${lesson.groupNumber} ${lesson.subgroup}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,

                )


        }

    }


}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationBottomBar(
    mLocalContext: Context, navController: NavController, pagerState: PagerState, calendar: Calendar
) {

    val coroutineScope = rememberCoroutineScope()

    val mDatePickerDialog = DatePickerDialog(
        mLocalContext,
        { _: DatePicker, mYearOfLife: Int, mMonthOfYear: Int, mDayOfMonth: Int ->

            calendar.set(mYearOfLife, mMonthOfYear, mDayOfMonth)
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                calendar.add(Calendar.DATE, 1)
            }

            if (pagerState.currentPage == calendar.get(Calendar.DAY_OF_WEEK) - 2) coroutineScope.launch {
                pagerState.scrollToPage(pagerState.currentPage + 1)
                pagerState.scrollToPage(pagerState.currentPage - 1)
            }
            else coroutineScope.launch {
                pagerState.animateScrollToPage(calendar.get(Calendar.DAY_OF_WEEK) - 2)
            }


        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(onClick = {
                mDatePickerDialog.show()

            })
        ) {
            Icon(
                Icons.Filled.DateRange, contentDescription = "Date",
                modifier = Modifier.size(25.dp),
            )
            Text("Date", style = MaterialTheme.typography.body1, color = Color.Black)


        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(25.dp)
            )
            Text("Home", style = MaterialTheme.typography.body1, color = Color.Black)


        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(onClick = {
                navController.navigate("menu_screen")
            })
        ) {
            Icon(
                Icons.Filled.Menu, contentDescription = "Menu", modifier = Modifier.size(25.dp)
            )
            Text("Menu", style = MaterialTheme.typography.body1, color = Color.Black)

        }
    }
}


fun parseDate(calendar: Calendar) =
    if (calendar.get(Calendar.MONTH) < 10 && calendar.get(Calendar.DAY_OF_MONTH) > 10) {

        "${calendar.get(Calendar.YEAR)}-0${calendar.get(Calendar.MONTH) + 1}-${
            calendar.get(
                Calendar.DAY_OF_MONTH
            )
        }"

    } else if (calendar.get(Calendar.DAY_OF_MONTH) < 10 && calendar.get(Calendar.MONTH) > 10) {

        "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-0${
            calendar.get(
                Calendar.DAY_OF_MONTH
            )
        }"

    } else if (calendar.get(Calendar.DAY_OF_MONTH) < 10 && calendar.get(Calendar.MONTH) < 10) {
        "${calendar.get(Calendar.YEAR)}-0${calendar.get(Calendar.MONTH) + 1}-0${
            calendar.get(
                Calendar.DAY_OF_MONTH
            )
        }"
    } else {
        "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-0${
            calendar.get(
                Calendar.DAY_OF_MONTH
            )
        }"
    }
