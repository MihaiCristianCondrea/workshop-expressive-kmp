package com.design.workshop.expressive.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkScheme = darkColorScheme(
    primary = WsColors.Purple,
    secondary = WsColors.Blue,
    tertiary = WsColors.Green,
    background = WsColors.DarkBackground,
    surface = WsColors.DarkSurface,
    surfaceVariant = WsColors.DarkSurfaceVariant,
    error = WsColors.Red,
    onPrimary = WsColors.TextPrimaryDark,
    onSecondary = WsColors.TextPrimaryDark,
    onBackground = WsColors.TextPrimaryDark,
    onSurface = WsColors.TextPrimaryDark,
    onSurfaceVariant = WsColors.TextSecondaryDark,
)

private val LightScheme = lightColorScheme(
    primary = WsColors.Purple,
    secondary = WsColors.Blue,
    tertiary = WsColors.Green,
    background = WsColors.LightBackground,
    surface = WsColors.LightSurface,
    surfaceVariant = WsColors.LightSurfaceVariant,
    error = WsColors.Red,
    onPrimary = WsColors.TextPrimaryDark,
    onSecondary = WsColors.TextPrimaryDark,
    onBackground = WsColors.TextPrimaryLight,
    onSurface = WsColors.TextPrimaryLight,
    onSurfaceVariant = WsColors.TextSecondaryLight,
)

@Composable
fun WorkshopTheme(
    darkTheme: Boolean = true,
    spacing: WsSpacing = WsSpacing(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalWsSpacing provides spacing) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkScheme else LightScheme,
            typography = WsTypography,
            shapes = WsShapes,
            content = content,
        )
    }
}

object WorkshopThemeTokens {
    val spacing: WsSpacing
        @Composable get() = LocalWsSpacing.current
}
