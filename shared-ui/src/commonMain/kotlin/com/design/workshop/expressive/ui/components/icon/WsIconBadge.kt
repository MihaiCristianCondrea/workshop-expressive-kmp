package com.design.workshop.expressive.ui.components.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens

/** Atlas-aligned icon badge sizes for shared learning UI atoms. */
enum class WsIconBadgeSize(val dp: Dp) {
    Small(38.dp),
    Medium(48.dp),
    Large(72.dp),
}

/**
 * Centers a short text or symbol in a tinted shape.
 *
 * This multiplatform atom can replace Atlas-style `SmallIcon`, `CourseIcon`, or stat-card
 * circular icon containers. Pass a Remix icon glyph as [text], an emoji, initials, or any short
 * label when an `ImageVector` is not required.
 */
@Composable
fun WsIconBadge(
    text: String,
    color: Color,
    size: Dp,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    fontSize: TextUnit = TextUnit.Unspecified,
    backgroundAlpha: Float = WorkshopThemeTokens.alpha.soft,
    contentColor: Color = color,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color = color.copy(alpha = backgroundAlpha), shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

/** Convenience overload using the Atlas badge size scale. */
@Composable
fun WsIconBadge(
    text: String,
    color: Color,
    size: WsIconBadgeSize,
    modifier: Modifier = Modifier,
    shape: Shape = if (size == WsIconBadgeSize.Small) CircleShape else MaterialTheme.shapes.large,
    fontSize: TextUnit = when (size) {
        WsIconBadgeSize.Small -> 14.sp
        WsIconBadgeSize.Medium -> 18.sp
        WsIconBadgeSize.Large -> 26.sp
    },
    backgroundAlpha: Float = WorkshopThemeTokens.alpha.soft,
    contentColor: Color = color,
) = WsIconBadge(
    text = text,
    color = color,
    size = size.dp,
    modifier = modifier,
    shape = shape,
    fontSize = fontSize,
    backgroundAlpha = backgroundAlpha,
    contentColor = contentColor,
)
