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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Circular avatar for initials or short labels.
 *
 * Provide either [backgroundBrush] for a branded gradient avatar or [backgroundColor] for a flat
 * Atlas-style tinted avatar. The component uses only common Compose APIs and is safe on Android,
 * iOS, JVM, JS, and Wasm targets.
 */
@Composable
fun WsAvatar(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = WsIconBadgeSize.Medium.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundBrush: Brush? = null,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = 16.sp,
) {
    val backgroundModifier = if (backgroundBrush != null) {
        Modifier.background(brush = backgroundBrush, shape = CircleShape)
    } else {
        Modifier.background(color = backgroundColor, shape = CircleShape)
    }

    Box(
        modifier = modifier
            .size(size)
            .then(backgroundModifier),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
