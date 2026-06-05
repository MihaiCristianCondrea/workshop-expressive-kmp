package com.design.workshop.expressive.ui.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.design.workshop.expressive.ui.theme.WsColors

enum class WsBadgeTone {
    Neutral,
    Info,
    Success,
    Warning,
    Danger,
}

@Composable
fun WsBadge(
    text: String,
    modifier: Modifier = Modifier,
    tone: WsBadgeTone = WsBadgeTone.Neutral,
) {
    val background = tone.containerColor
    val foreground = tone.contentColor
    val radius = WorkshopThemeTokens.radius

    Text(
        text = text,
        modifier = modifier
            .background(background, RoundedCornerShape(radius.xs))
            .padding(PaddingValues(horizontal = 8.dp, vertical = 2.dp)),
        color = foreground,
        style = MaterialTheme.typography.labelSmall,
    )
}

private val WsBadgeTone.containerColor: Color
    get() = when (this) {
        WsBadgeTone.Neutral -> WsColors.DarkSurfaceVariant
        WsBadgeTone.Info -> WsColors.Blue.copy(alpha = 0.18f)
        WsBadgeTone.Success -> WsColors.Green.copy(alpha = 0.18f)
        WsBadgeTone.Warning -> WsColors.Orange.copy(alpha = 0.18f)
        WsBadgeTone.Danger -> WsColors.Red.copy(alpha = 0.18f)
    }

private val WsBadgeTone.contentColor: Color
    get() = when (this) {
        WsBadgeTone.Neutral -> WsColors.TextPrimaryDark
        WsBadgeTone.Info -> WsColors.Blue
        WsBadgeTone.Success -> WsColors.Green
        WsBadgeTone.Warning -> WsColors.Orange
        WsBadgeTone.Danger -> WsColors.Red
    }
