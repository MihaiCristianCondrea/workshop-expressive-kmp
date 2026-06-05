package com.design.workshop.expressive.ui.components.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowDownSLine
import com.design.workshop.expressive.ui.components.progress.WsLinearProgress
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.design.workshop.expressive.ui.theme.WsColors

@Immutable
data class WsDrawerItem(
    val id: String,
    val title: String,
    val icon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
    val subtitle: String? = null,
    val badge: String? = null,
    val progress: Float? = null,
    val enabled: Boolean = true,
    val initiallyExpanded: Boolean = false,
    val children: List<WsDrawerItem> = emptyList(),
)

@Composable
fun WsDrawer(
    primaryItems: List<WsDrawerItem>, // FIXME:Parameter 'primaryItems' has runtime-determined stability
    modifier: Modifier = Modifier,
    selectedItemId: String? = null,
    collapsibleItems: List<WsDrawerItem> = emptyList(), // FIXME: Parameter 'collapsibleItems' has runtime-determined stability
    onItemClick: (WsDrawerItem) -> Unit = {},
    width: Dp = 280.dp,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
) {
    val spacing = WorkshopThemeTokens.spacing
    val initiallyExpandedIds = remember(primaryItems, collapsibleItems) {
        (primaryItems + collapsibleItems).collectInitiallyExpandedIds()
    }
    var expandedIds by remember(initiallyExpandedIds) { mutableStateOf(initiallyExpandedIds) }

    Surface(
        modifier = modifier
            .width(width)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(WorkshopThemeTokens.radius.screen),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.18f)),
    ) {
        Column(
            modifier = Modifier.padding(spacing.xl),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            header?.invoke()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                primaryItems.forEach { item ->
                    WsDrawerItemRow(
                        item = item,
                        selectedItemId = selectedItemId,
                        expandedIds = expandedIds,
                        level = 0,
                        onItemClick = onItemClick,
                        onToggleExpanded = { toggledId ->
                            expandedIds = if (toggledId in expandedIds) {
                                expandedIds - toggledId
                            } else {
                                expandedIds + toggledId
                            }
                        },
                    )
                }

                if (primaryItems.isNotEmpty() && collapsibleItems.isNotEmpty()) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = spacing.md),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.14f),
                    )
                }

                collapsibleItems.forEach { item ->
                    WsDrawerItemRow(
                        item = item,
                        selectedItemId = selectedItemId,
                        expandedIds = expandedIds,
                        level = 0,
                        onItemClick = onItemClick,
                        onToggleExpanded = { toggledId ->
                            expandedIds = if (toggledId in expandedIds) {
                                expandedIds - toggledId
                            } else {
                                expandedIds + toggledId
                            }
                        },
                    )
                }
            }

            footer?.invoke()
        }
    }
}

@Composable
private fun WsDrawerItemRow(
    item: WsDrawerItem,
    selectedItemId: String?,
    expandedIds: Set<String>,
    level: Int,
    onItemClick: (WsDrawerItem) -> Unit,
    onToggleExpanded: (String) -> Unit,
) {
    val spacing = WorkshopThemeTokens.spacing
    val radius = WorkshopThemeTokens.radius
    val hasChildren = item.children.isNotEmpty()
    val selected = item.id == selectedItemId
    val expanded = item.id in expandedIds
    val indentation = (level * 18).dp

    val selectionAlpha by animateFloatAsState(if (selected) 1f else 0f, label = "Selection Background Alpha")
    val arrowRotation by animateFloatAsState(if (expanded) 180f else 0f, label = "Arrow Rotation")
    val settingsIconRotation by animateFloatAsState(
        targetValue = if (item.id == "settings" && selected) 180f else 0f,
        label = "Settings Icon Rotation",
    )

    val contentColor by animateColorAsState(
        targetValue = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
        label = "Content Color"
    )
    val variantColor by animateColorAsState(
        targetValue = if (selected) Color.White.copy(alpha = 0.82f) else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "Variant Content Color"
    )

    Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(if (level == 0) radius.md else radius.sm))
                .drawWithCache {
                    onDrawBehind {
                        if (selectionAlpha > 0f) {
                            drawRect(
                                brush = selectedBrush,
                                alpha = selectionAlpha
                            )
                        }
                    }
                }
                .clickable(enabled = item.enabled) {
                    if (hasChildren) {
                        onToggleExpanded(item.id)
                    } else {
                        onItemClick(item)
                    }
                }
                .padding(
                    start = spacing.md + indentation,
                    top = if (level == 0) spacing.md else spacing.sm,
                    end = spacing.sm,
                    bottom = if (level == 0) spacing.md else spacing.sm,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val displayIcon = if (selected && item.selectedIcon != null) item.selectedIcon else item.icon
            if (displayIcon != null) {
                Icon(
                    imageVector = displayIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(if (level == 0) 20.dp else 16.dp)
                        .graphicsLayer { rotationZ = settingsIconRotation },
                    tint = variantColor,
                )
                Spacer(Modifier.width(spacing.md))
            } else if (level > 0) {
                Box(
                    modifier = Modifier
                        .size(if (selected) 7.dp else 5.dp)
                        .background(
                            color = variantColor.copy(alpha = if (selected) 1f else 0.55f),
                            shape = CircleShape,
                        ),
                )
                Spacer(Modifier.width(spacing.md))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    color = contentColor,
                    style = if (level == 0) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.labelMedium,
                    fontWeight = if (selected || hasChildren) FontWeight.SemiBold else FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (item.subtitle != null) {
                    Text(
                        text = item.subtitle,
                        color = variantColor,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                if (item.progress != null) {
                    Spacer(Modifier.height(spacing.sm))
                    WsLinearProgress(
                        progress = item.progress,
                        height = 4.dp,
                        progressColor = if (selected) Color.White else MaterialTheme.colorScheme.primary,
                        trackColor = if (selected) Color.White.copy(alpha = 0.28f) else MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }

            if (item.badge != null) {
                Text(
                    text = item.badge,
                    modifier = Modifier
                        .padding(start = spacing.sm)
                        .background(
                            color = if (selected) Color.White.copy(alpha = 0.18f) else MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(radius.xs),
                        )
                        .padding(horizontal = spacing.sm, vertical = 2.dp),
                    color = if (selected) Color.White else MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            if (hasChildren) {
                Icon(
                    imageVector = Remix.Arrows.ArrowDownSLine,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = spacing.sm)
                        .size(20.dp)
                        .graphicsLayer { rotationZ = arrowRotation },
                    tint = variantColor,
                )
            }
        }

        AnimatedVisibility(
            visible = hasChildren && expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                item.children.forEach { child ->
                    WsDrawerItemRow(
                        item = child,
                        selectedItemId = selectedItemId,
                        expandedIds = expandedIds,
                        level = level + 1,
                        onItemClick = onItemClick,
                        onToggleExpanded = onToggleExpanded,
                    )
                }
            }
        }
    }
}

private val selectedBrush = Brush.horizontalGradient(
    colors = listOf(WsColors.Purple, WsColors.PurpleDark),
)

private fun List<WsDrawerItem>.collectInitiallyExpandedIds(): Set<String> = buildSet {
    fun visit(item: WsDrawerItem) {
        if (item.initiallyExpanded) add(item.id)
        item.children.forEach(::visit)
    }

    forEach(::visit)
}
