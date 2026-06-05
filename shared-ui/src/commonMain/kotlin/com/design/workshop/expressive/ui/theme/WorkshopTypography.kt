package com.design.workshop.expressive.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.design.workshop.expressive.shared_ui.generated.resources.Res
import com.design.workshop.expressive.shared_ui.generated.resources.google_sans_flex_variable
import org.jetbrains.compose.resources.Font

@Composable
fun getWorkshopFontFamily() = FontFamily(
    Font(Res.font.google_sans_flex_variable, FontWeight.Normal),
    Font(Res.font.google_sans_flex_variable, FontWeight.Medium),
    Font(Res.font.google_sans_flex_variable, FontWeight.SemiBold),
    Font(Res.font.google_sans_flex_variable, FontWeight.Bold),
)

val WsCodeTextStyle = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 13.sp,
    lineHeight = 22.sp,
)

@Composable
fun getWorkshopTypography(): Typography {
    val fontFamily = getWorkshopFontFamily()
    return Typography(
        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.25).sp,
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
        ),
        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        labelMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
    )
}
