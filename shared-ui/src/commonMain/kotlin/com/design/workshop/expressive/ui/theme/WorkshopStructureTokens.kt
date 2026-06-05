package com.design.workshop.expressive.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Radius scale for Workshop Expressive learning surfaces and controls. */
@Immutable
data class WsRadius(
    val xs: Dp = 2.dp,
    val sm: Dp = 4.dp,
    val md: Dp = 6.dp,
    val lg: Dp = 8.dp,
    val xl: Dp = 12.dp,
    val xxl: Dp = 14.dp,
    val screen: Dp = 16.dp,
    val pill: Dp = 999.dp,
)

/** Border widths used by shared Workshop components. */
@Immutable
data class WsBorder(
    val thin: Dp = 1.dp,
)

/** Elevation values for cards, shells, and drawer-like surfaces. */
@Immutable
data class WsElevation(
    val card: Dp = 0.dp,
    val shell: Dp = 0.dp,
    val drawer: Dp = 0.dp,
)

/** Alpha roles for soft fills and selected overlays. */
@Immutable
data class WsAlpha(
    val soft: Float = 0.12f,
    val subtle: Float = 0.10f,
    val selectedOverlay: Float = 0.18f,
)

val LocalWsRadius = staticCompositionLocalOf { WsRadius() }
val LocalWsBorder = staticCompositionLocalOf { WsBorder() }
val LocalWsElevation = staticCompositionLocalOf { WsElevation() }
val LocalWsAlpha = staticCompositionLocalOf { WsAlpha() }
