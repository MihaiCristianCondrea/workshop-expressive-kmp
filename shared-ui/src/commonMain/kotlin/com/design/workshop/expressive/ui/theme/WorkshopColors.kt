package com.design.workshop.expressive.ui.theme

import androidx.compose.ui.graphics.Color

object WsColors {
    // Brighter, cleaner accents shared by both themes.
    val Purple = Color(0xFF7C5CFF)
    val PurpleDark = Color(0xFF6547F5)
    val Blue = Color(0xFF2F80FF)
    val Green = Color(0xFF19C98B)
    val Orange = Color(0xFFFFA62B)
    val Red = Color(0xFFFF4D5E)

    // True-black dark theme. Surfaces remain only slightly lifted from black so
    // hierarchy is visible without turning the theme back into dark navy/gray.
    val DarkBackground = Color(0xFF000000)
    val DarkSurface = Color(0xFF0A0A0C)
    val DarkSurfaceVariant = Color(0xFF151519)

    // Neutral, slightly softer light theme that lets the accent colors lead.
    val LightBackground = Color(0xFFF8F9FC)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF0F2F7)

    val LightPrimarySoft = Color(0xFFF0ECFF)
    val LightBorderSubtle = Color(0xFFDDE1EA)
    val LightCodeBackground = Color(0xFFF4F5F9)
    val LightProgressTrack = Color(0xFFE3E6ED)
    val LightStateUpcoming = Color(0xFF9299A8)
    val LightGradientAccent = Blue

    val DarkPrimarySoft = Color(0xFF21184A)
    val DarkBorderSubtle = Color(0xFF29292F)
    val DarkCodeBackground = Color(0xFF08080A)
    val DarkProgressTrack = Color(0xFF242429)
    val DarkStateUpcoming = Color(0xFF777783)
    val DarkGradientAccent = Color(0xFF59A5FF)

    /** Atlas-inspired light-theme semantic alias for soft primary containers. */
    val PrimarySoft = LightPrimarySoft

    /** Atlas-inspired light-theme semantic alias for subtle one-pixel borders. */
    val BorderSubtle = LightBorderSubtle

    /** Atlas-inspired light-theme semantic alias for code and search-field surfaces. */
    val CodeBackground = LightCodeBackground

    /** Atlas-inspired light-theme semantic alias for progress-track fills. */
    val ProgressTrack = LightProgressTrack

    /** Atlas-inspired light-theme semantic alias for disabled or upcoming states. */
    val StateUpcoming = LightStateUpcoming

    /** Atlas-inspired light-theme semantic alias for secondary gradient stops. */
    val GradientAccent = LightGradientAccent

    val TextPrimaryDark = Color(0xFFF7F7FA)
    val TextSecondaryDark = Color(0xFFA4A4AF)

    val TextPrimaryLight = Color(0xFF17171C)
    val TextSecondaryLight = Color(0xFF626875)
}
