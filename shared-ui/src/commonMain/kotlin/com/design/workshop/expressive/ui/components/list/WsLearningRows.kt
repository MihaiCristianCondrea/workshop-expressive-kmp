package com.design.workshop.expressive.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.design.workshop.expressive.ui.theme.WsColors

/** Learning-progress status values for chapter, checklist, and lesson-tree rows. */
enum class WsStatus { Complete, Current, Upcoming }

/**
 * Compact label/value row for course metadata.
 *
 * Pass a Remix icon glyph, emoji, or other short [icon] text. The text fallback keeps this component
 * compatible with every Compose Multiplatform target and with apps that do not opt into vector icons.
 */
@Composable
fun WsMetadataRow(
    icon: String,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.sm),
    ) {
        Text(text = icon, style = MaterialTheme.typography.labelLarge)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/**
 * Atlas-style chapter/status row with selected and upcoming state treatment.
 *
 * Defaults use `✓`, `▶`, and `○` as status symbols; provide [statusSymbol] to override, or pass a
 * Remix icon glyph string from your app if desired.
 */
@Composable
fun WsStatusRow(
    number: String?,
    title: String,
    status: WsStatus,
    selected: Boolean,
    modifier: Modifier = Modifier,
    statusSymbol: String = defaultStatusSymbol(status),
) {
    val alpha = WorkshopThemeTokens.alpha
    val upcomingColor = if (MaterialTheme.colorScheme.background == WsColors.DarkBackground) {
        WsColors.DarkStateUpcoming
    } else {
        WsColors.LightStateUpcoming
    }
    val statusColor = when (status) {
        WsStatus.Complete -> MaterialTheme.colorScheme.tertiary
        WsStatus.Current -> MaterialTheme.colorScheme.primary
        WsStatus.Upcoming -> upcomingColor
    }
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(WorkshopThemeTokens.radius.md))
            .padding(horizontal = WorkshopThemeTokens.spacing.md, vertical = WorkshopThemeTokens.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.sm),
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(statusColor.copy(alpha = if (selected) alpha.selectedOverlay else alpha.soft), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = statusSymbol,
                color = statusColor,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
            )
        }
        if (number != null) {
            Text(
                text = number,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = if (status == WsStatus.Upcoming) {
                upcomingColor
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

fun defaultStatusSymbol(status: WsStatus): String = when (status) {
    WsStatus.Complete -> "✓"
    WsStatus.Current -> "▶"
    WsStatus.Upcoming -> "○"
}
