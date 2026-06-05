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
    primaryContainer = WsColors.DarkPrimarySoft,
    outline = WsColors.DarkBorderSubtle,
    outlineVariant = WsColors.DarkProgressTrack,
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
    primaryContainer = WsColors.LightPrimarySoft,
    outline = WsColors.LightBorderSubtle,
    outlineVariant = WsColors.LightProgressTrack,
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
    radius: WsRadius = WsRadius(),
    border: WsBorder = WsBorder(),
    elevation: WsElevation = WsElevation(),
    alpha: WsAlpha = WsAlpha(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalWsSpacing provides spacing,
        LocalWsRadius provides radius,
        LocalWsBorder provides border,
        LocalWsElevation provides elevation,
        LocalWsAlpha provides alpha,
    ) {
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

    val radius: WsRadius
        @Composable get() = LocalWsRadius.current

    val border: WsBorder
        @Composable get() = LocalWsBorder.current

    val elevation: WsElevation
        @Composable get() = LocalWsElevation.current

    val alpha: WsAlpha
        @Composable get() = LocalWsAlpha.current
}
