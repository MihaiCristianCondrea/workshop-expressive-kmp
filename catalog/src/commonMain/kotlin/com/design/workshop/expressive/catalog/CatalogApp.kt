package com.design.workshop.expressive.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import org.jetbrains.compose.resources.painterResource
import com.design.workshop.expressive.shared_ui.generated.resources.Res
import com.design.workshop.expressive.shared_ui.generated.resources.workshop_expressive_logo
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.Development
import com.woowla.compose.icon.collections.remix.remix.Document
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowRightLine
import com.woowla.compose.icon.collections.remix.remix.development.TerminalWindowFill
import com.woowla.compose.icon.collections.remix.remix.development.TerminalWindowLine
import com.woowla.compose.icon.collections.remix.remix.document.BookFill
import com.woowla.compose.icon.collections.remix.remix.document.BookLine
import com.woowla.compose.icon.collections.remix.remix.document.FolderFill
import com.woowla.compose.icon.collections.remix.remix.document.FolderLine
import com.woowla.compose.icon.collections.remix.remix.system.DashboardFill
import com.woowla.compose.icon.collections.remix.remix.system.DashboardLine
import com.woowla.compose.icon.collections.remix.remix.system.SettingsFill
import com.woowla.compose.icon.collections.remix.remix.system.SettingsLine
import com.design.workshop.expressive.ui.components.button.WsButton
import com.design.workshop.expressive.ui.components.button.WsButtonSize
import com.design.workshop.expressive.ui.components.button.WsButtonVariant
import com.design.workshop.expressive.ui.components.card.WsCard
import com.design.workshop.expressive.ui.components.drawer.WsDrawer
import com.design.workshop.expressive.ui.components.drawer.WsDrawerItem
import com.design.workshop.expressive.ui.components.input.WsTextField
import com.design.workshop.expressive.ui.components.navigation.WsBreadcrumbs
import com.design.workshop.expressive.ui.components.navigation.WsCrumb
import com.design.workshop.expressive.ui.components.progress.WsCircularProgress
import com.design.workshop.expressive.ui.components.progress.WsLinearProgress
import com.design.workshop.expressive.ui.theme.WorkshopTheme
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CatalogApp() {
    WorkshopTheme {
        ComponentGallery()
    }
}

@Composable
fun ComponentGallery() {
    val spacing = WorkshopThemeTokens.spacing
    var input by remember { mutableStateOf("Design system first") }
    var selectedDrawerItem by remember { mutableStateOf("state-ui") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.xxl),
        horizontalArrangement = Arrangement.spacedBy(spacing.xl),
    ) {
        WsDrawer(
            primaryItems = demoPrimaryItems,
            collapsibleItems = demoCourseItems,
            selectedItemId = selectedDrawerItem,
            onItemClick = { selectedDrawerItem = it.id },
            header = { DrawerHeader() },
            footer = { DrawerFooter() },
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xl),
        ) {
            WsBreadcrumbs(
                crumbs = listOf(
                    WsCrumb(id = "home", label = "Home"),
                    WsCrumb(id = "ui-kit", label = "Expressive UI Kit"),
                    WsCrumb(id = "catalog", label = "Component Gallery"),
                )
            )

            Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Work",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Shop",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.width(spacing.sm))
                    Text(
                        text = "Expressive UI Kit",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            WsCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.lg)) {
                    Text(
                        text = "Color Palette",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.md),
                    ) {
                        ColorSwatch("Primary", MaterialTheme.colorScheme.primary)
                        ColorSwatch("Secondary", MaterialTheme.colorScheme.secondary)
                        ColorSwatch("Tertiary", MaterialTheme.colorScheme.tertiary)
                        ColorSwatch("Surface", MaterialTheme.colorScheme.surface)
                        ColorSwatch("Background", MaterialTheme.colorScheme.background)
                        ColorSwatch("Error", MaterialTheme.colorScheme.error)
                    }
                }
            }

            WsCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
                    Text(
                        text = "Buttons",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                        WsButton(text = "Primary", onClick = {}, variant = WsButtonVariant.Primary)
                        WsButton(text = "Secondary", onClick = {}, variant = WsButtonVariant.Secondary)
                        WsButton(text = "Outlined", onClick = {}, variant = WsButtonVariant.Outlined)
                        WsButton(text = "Ghost", onClick = {}, variant = WsButtonVariant.Ghost)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                        WsButton(text = "Small", onClick = {}, size = WsButtonSize.Small)
                        WsButton(text = "Medium", onClick = {}, size = WsButtonSize.Medium)
                        WsButton(text = "Large", onClick = {}, size = WsButtonSize.Large)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                        WsButton(
                            onClick = {},
                            icon = Remix.Arrows.ArrowRightLine,
                            contentDescription = "Open",
                        )
                        WsButton(
                            onClick = {},
                            icon = Remix.System.SettingsLine,
                            contentDescription = "Settings",
                            variant = WsButtonVariant.Secondary,
                        )
                        WsButton(
                            text = "Next",
                            onClick = {},
                            icon = Remix.Arrows.ArrowRightLine,
                            variant = WsButtonVariant.Outlined,
                        )
                        WsButton(
                            text = "Settings",
                            onClick = {},
                            icon = Remix.System.SettingsLine,
                            variant = WsButtonVariant.Ghost,
                        )
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl)) {
                WsCard(modifier = Modifier.weight(1f)) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                        Text(
                            text = "Progress",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        WsLinearProgress(
                            progress = 0.68f,
                            label = "Kotlin Fundamentals",
                            showLabel = true,
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(spacing.lg)) {
                            WsCircularProgress(progress = 0.68f)
                            WsCircularProgress()
                        }
                    }
                }

                WsCard(modifier = Modifier.weight(1f)) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                        Text(
                            text = "Input",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        WsTextField(
                            value = input,
                            onValueChange = { input = it },
                            label = "Lesson note",
                            placeholder = "Type a note",
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing.xs))
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Foundation:", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("tokens", color = MaterialTheme.colorScheme.primary)
                Icon(
                    imageVector = Remix.Arrows.ArrowRightLine,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text("atoms", color = MaterialTheme.colorScheme.primary)
                Icon(
                    imageVector = Remix.Arrows.ArrowRightLine,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text("molecules", color = MaterialTheme.colorScheme.primary)
                Icon(
                    imageVector = Remix.Arrows.ArrowRightLine,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text("screens", color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(spacing.xs))
            }
        }
    }
}

@Composable
private fun ColorSwatch(name: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.xs),
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color, MaterialTheme.shapes.small)
        )
        Text(text = name, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun DrawerHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.md)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.sm)
        ) {
            Image(
                painter = painterResource(Res.drawable.workshop_expressive_logo),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                Row {
                    Text(
                        text = "Work",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Shop",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = "Expressive",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun DrawerFooter() {
    WsCard(
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Pro Plan",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "Renews in 12 days",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
            WsButton(
                text = "Manage",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                size = WsButtonSize.Small,
                variant = WsButtonVariant.Outlined,
            )
        }
    }
}

private val demoPrimaryItems = persistentListOf(
    WsDrawerItem(id = "home", title = "Home", icon = Remix.System.DashboardLine, selectedIcon = Remix.System.DashboardFill),
    WsDrawerItem(id = "courses", title = "Courses", icon = Remix.Document.BookLine, selectedIcon = Remix.Document.BookFill),
    WsDrawerItem(id = "workshops", title = "Workshops", icon = Remix.Development.TerminalWindowLine, selectedIcon = Remix.Development.TerminalWindowFill, badge = "New"),
    WsDrawerItem(id = "projects", title = "Projects", icon = Remix.Document.FolderLine, selectedIcon = Remix.Document.FolderFill),
    WsDrawerItem(id = "settings", title = "Settings", icon = Remix.System.SettingsLine, selectedIcon = Remix.System.SettingsFill),
)

private val demoCourseItems = persistentListOf(
    WsDrawerItem(
        id = "android-development",
        title = "Android Development",
        subtitle = "18 / 32 lessons",
        progress = 0.56f,
        initiallyExpanded = true,
        children = persistentListOf(
            WsDrawerItem(
                id = "chapter-1",
                title = "Chapter 1: Getting Started",
                initiallyExpanded = true,
                children = persistentListOf(
                    WsDrawerItem(id = "intro", title = "1.1 Introduction"),
                    WsDrawerItem(id = "environment", title = "1.2 Environment Setup"),
                    WsDrawerItem(id = "first-app", title = "1.3 Your First App"),
                ),
            ),
            WsDrawerItem(
                id = "chapter-2",
                title = "Chapter 2: Kotlin Basics",
                initiallyExpanded = true,
                children = persistentListOf(
                    WsDrawerItem(id = "variables", title = "2.1 Variables & Types"),
                    WsDrawerItem(id = "functions", title = "2.2 Functions"),
                    WsDrawerItem(id = "control-flow", title = "2.3 Control Flow"),
                    WsDrawerItem(id = "state-ui", title = "2.4 State and UI"),
                    WsDrawerItem(id = "quiz", title = "2.5 Quiz"),
                ),
            ),
            WsDrawerItem(id = "chapter-3", title = "Chapter 3: UI with Compose"),
            WsDrawerItem(id = "chapter-4", title = "Chapter 4: Navigation"),
        ),
    ),
)
