package com.design.workshop.expressive.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.components.badge.WsBadge
import com.design.workshop.expressive.ui.components.badge.WsBadgeTone
import com.design.workshop.expressive.ui.components.button.WsButton
import com.design.workshop.expressive.ui.components.button.WsButtonSize
import com.design.workshop.expressive.ui.components.button.WsButtonVariant
import com.design.workshop.expressive.ui.components.card.WsCard
import com.design.workshop.expressive.ui.components.input.WsTextField
import com.design.workshop.expressive.ui.theme.WorkshopTheme
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens

@Composable
fun App() {
    WorkshopTheme(darkTheme = true) {
        ComponentGallery()
    }
}

@Composable
fun ComponentGallery() {
    val spacing = WorkshopThemeTokens.spacing
    var input by remember { mutableStateOf("Design system first") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.xxl),
        verticalArrangement = Arrangement.spacedBy(spacing.xl),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            WsBadge(text = "Milestone 1", tone = WsBadgeTone.Info)
            Text(
                text = "WorkShop Expressive UI Kit",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Shared tokens and wrapped components for the Kotlin Multiplatform frontend.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
            )
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
                    WsButton(text = "Tertiary", onClick = {}, variant = WsButtonVariant.Tertiary)
                    WsButton(text = "Ghost", onClick = {}, variant = WsButtonVariant.Ghost)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                    WsButton(text = "Small", onClick = {}, size = WsButtonSize.Small)
                    WsButton(text = "Medium", onClick = {}, size = WsButtonSize.Medium)
                    WsButton(text = "Large", onClick = {}, size = WsButtonSize.Large)
                    WsButton(text = "Loading", onClick = {}, loading = true)
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl)) {
            WsCard(modifier = Modifier.weight(1f)) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Badges",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                        WsBadge(text = "Neutral")
                        WsBadge(text = "Info", tone = WsBadgeTone.Info)
                        WsBadge(text = "Success", tone = WsBadgeTone.Success)
                        WsBadge(text = "Warning", tone = WsBadgeTone.Warning)
                        WsBadge(text = "Danger", tone = WsBadgeTone.Danger)
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
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
            Text("Foundation:", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("tokens → atoms → molecules → screens", color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(spacing.xs))
        }
    }
}
