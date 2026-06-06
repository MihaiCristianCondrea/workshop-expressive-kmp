package com.design.workshop.expressive.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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

val LocalWsBorder = staticCompositionLocalOf { WsBorder() }
val LocalWsElevation = staticCompositionLocalOf { WsElevation() }
val LocalWsAlpha = staticCompositionLocalOf { WsAlpha() }
