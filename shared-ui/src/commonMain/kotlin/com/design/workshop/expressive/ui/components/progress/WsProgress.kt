package com.design.workshop.expressive.ui.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens

@Composable
fun WsLinearProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 6.dp,
    showLabel: Boolean = false,
    label: String? = null,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    val spacing = WorkshopThemeTokens.spacing
    val safeProgress = progress.coerceIn(0f, 1f)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (showLabel || label != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = label.orEmpty(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "${(safeProgress * 100).toInt()}%",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(trackColor, CircleShape),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(safeProgress)
                    .height(height)
                    .background(progressColor, CircleShape),
            )
        }
    }
}

@Composable
fun WsCircularProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    size: Dp = 32.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    if (progress == null) {
        CircularProgressIndicator(
            modifier = modifier.size(size),
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
        )
    } else {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = modifier.size(size),
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
        )
    }
}
