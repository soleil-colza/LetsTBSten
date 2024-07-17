package com.example.tbstenjam.shared.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun getColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    light: ColorScheme,
    dark: ColorScheme,
): ColorScheme =
    /* not support dynamicColor */
    when {
        darkTheme -> dark
        else -> light
    }
