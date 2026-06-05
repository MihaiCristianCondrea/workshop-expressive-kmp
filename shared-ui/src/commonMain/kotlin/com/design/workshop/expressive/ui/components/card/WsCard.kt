package com.design.workshop.expressive.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.design.workshop.expressive.ui.theme.WsColors

/** Variants for Workshop card surfaces used by learning applications. */
enum class WsCardVariant {
    Default,
    Screen,
    Panel,
    Elevated,
    Code,
}

/**
 * Shared Workshop card surface.
 *
 * Use [WsCardVariant.Screen] for Atlas-style screen cards with a large radius, subtle border,
 * surface background, 22.dp padding, and low shadow. Use [WsCardVariant.Code] for code snippets
 * or preformatted content that should sit on the semantic code-background token.
 */
@Composable
fun WsCard(
    modifier: Modifier = Modifier,
    variant: WsCardVariant = WsCardVariant.Default,
    containerColor: Color? = null,
    contentPadding: PaddingValues? = null,
    content: @Composable () -> Unit,
) {
    val radius = WorkshopThemeTokens.radius
    val spacing = WorkshopThemeTokens.spacing
    val border = WorkshopThemeTokens.border
    val elevation = WorkshopThemeTokens.elevation
    val alpha = WorkshopThemeTokens.alpha

    val shape: Shape = when (variant) {
        WsCardVariant.Default -> MaterialTheme.shapes.large
        WsCardVariant.Screen -> RoundedCornerShape(radius.xl)
        WsCardVariant.Panel -> RoundedCornerShape(radius.lg)
        WsCardVariant.Elevated -> RoundedCornerShape(radius.xl)
        WsCardVariant.Code -> RoundedCornerShape(radius.md)
    }
    val resolvedPadding = contentPadding ?: when (variant) {
        WsCardVariant.Default -> PaddingValues(spacing.xl)
        WsCardVariant.Screen -> PaddingValues(radius.xl)
        WsCardVariant.Panel -> PaddingValues(spacing.lg)
        WsCardVariant.Elevated -> PaddingValues(spacing.xl)
        WsCardVariant.Code -> PaddingValues(spacing.lg)
    }
    val resolvedContainerColor = containerColor ?: when (variant) {
        WsCardVariant.Code -> if (MaterialTheme.colorScheme.background == WsColors.DarkBackground) {
            WsColors.DarkCodeBackground
        } else {
            WsColors.LightCodeBackground
        }
        else -> MaterialTheme.colorScheme.surface
    }
    val resolvedElevation = when (variant) {
        WsCardVariant.Default -> elevation.card
        WsCardVariant.Screen -> elevation.card
        WsCardVariant.Panel -> elevation.card
        WsCardVariant.Elevated -> elevation.shell
        WsCardVariant.Code -> 0.dp
    }
    val borderAlpha = when (variant) {
        WsCardVariant.Default -> alpha.selectedOverlay
        WsCardVariant.Screen -> 1f
        WsCardVariant.Panel -> 0.55f
        WsCardVariant.Elevated -> 0.35f
        WsCardVariant.Code -> 0.75f
    }

    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = resolvedContainerColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = resolvedElevation,
        ),
        border = BorderStroke(border.thin, MaterialTheme.colorScheme.outline.copy(alpha = borderAlpha)),
    ) {
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(resolvedPadding)) {
            content()
        }
    }
}
