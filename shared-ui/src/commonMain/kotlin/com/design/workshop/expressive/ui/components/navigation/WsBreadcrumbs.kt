package com.design.workshop.expressive.ui.components.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowRightSLine

data class WsCrumb(
    val id: String,
    val label: String,
    val onClick: () -> Unit = {}
)

@Composable
fun WsBreadcrumbs(
    crumbs: List<WsCrumb>,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    val spacing = WorkshopThemeTokens.spacing
    val radius = WorkshopThemeTokens.radius

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs)
    ) {
        crumbs.forEachIndexed { index, crumb ->
            val isLast = index == crumbs.lastIndex
            
            Text(
                text = crumb.label,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isLast) MaterialTheme.colorScheme.onSurface else contentColor,
                fontWeight = if (isLast) FontWeight.SemiBold else FontWeight.Medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(radius.xs))
                    .clickable(enabled = !isLast) { crumb.onClick() }
                    .padding(horizontal = spacing.sm, vertical = spacing.xs)
            )

            if (!isLast) {
                Icon(
                    imageVector = Remix.Arrows.ArrowRightSLine,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = contentColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}
