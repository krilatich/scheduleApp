package com.example.schedule.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Green200,
    onPrimary = Blue200,
    secondary = MediumBlue,
    background = LightBlue,
    surface = Green200,
    onBackground = Blue200
)
@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Green200,
    onPrimary = Blue200,
    secondary = MediumBlue,
    background = LightBlue,
    surface = Green200,
    onBackground = Blue200


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ScheduleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}