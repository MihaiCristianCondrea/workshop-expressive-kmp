package com.design.workshop.expressive.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.util.bounceClick

enum class WsButtonVariant {
    Primary,
    Secondary,
    Outlined,
    Ghost,
}

enum class WsButtonSize {
    Small,
    Medium,
    Large,
}

@Composable
fun WsButton(
    text: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: WsButtonVariant = WsButtonVariant.Primary,
    size: WsButtonSize = WsButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    contentDescription: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val height = size.height
    val horizontalPadding = size.horizontalPadding
    val iconOnly = icon != null && text.isNullOrBlank()
    val buttonEnabled = enabled && !loading
    val resolvedModifier = modifier
        .heightIn(min = height)
        .defaultMinSize(minWidth = if (iconOnly) height else Dp.Unspecified)
        .bounceClick()
    val resolvedPadding = if (iconOnly) {
        PaddingValues(horizontal = 0.dp)
    } else {
        PaddingValues(horizontal = horizontalPadding)
    }

    val content: @Composable RowScope.() -> Unit = {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.defaultMinSize(minWidth = 18.dp, minHeight = 18.dp),
                strokeWidth = 2.dp,
                color = if (variant == WsButtonVariant.Primary) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.primary
                },
            )
        } else {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(size.iconSize),
                )
            }
            if (!text.isNullOrBlank()) {
                if (icon != null) {
                    Spacer(Modifier.width(size.iconSpacing))
                }
                Text(
                    text = text,
                    style = when (size) {
                        WsButtonSize.Small -> MaterialTheme.typography.labelSmall
                        WsButtonSize.Medium -> MaterialTheme.typography.labelMedium
                        WsButtonSize.Large -> MaterialTheme.typography.labelLarge
                    }
                )
            }
        }
    }

    when (variant) {
        WsButtonVariant.Primary -> Button(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = resolvedModifier,
            shape = MaterialTheme.shapes.medium,
            contentPadding = resolvedPadding,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            content = content,
        )

        WsButtonVariant.Secondary -> Button(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = resolvedModifier,
            shape = MaterialTheme.shapes.medium,
            contentPadding = resolvedPadding,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
            content = content,
        )

        WsButtonVariant.Outlined -> OutlinedButton(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = resolvedModifier,
            shape = MaterialTheme.shapes.medium,
            contentPadding = resolvedPadding,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
            ),
            interactionSource = interactionSource,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            content = content,
        )

        WsButtonVariant.Ghost -> TextButton(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = resolvedModifier,
            shape = MaterialTheme.shapes.medium,
            contentPadding = resolvedPadding,
            interactionSource = interactionSource,
            content = content,
        )
    }
}

private val WsButtonSize.height: Dp
    get() = when (this) {
        WsButtonSize.Small -> 36.dp
        WsButtonSize.Medium -> 44.dp
        WsButtonSize.Large -> 52.dp
    }

private val WsButtonSize.horizontalPadding: Dp
    get() = when (this) {
        WsButtonSize.Small -> 14.dp
        WsButtonSize.Medium -> 18.dp
        WsButtonSize.Large -> 24.dp
    }

private val WsButtonSize.iconSize: Dp
    get() = when (this) {
        WsButtonSize.Small -> 16.dp
        WsButtonSize.Medium -> 18.dp
        WsButtonSize.Large -> 20.dp
    }

private val WsButtonSize.iconSpacing: Dp
    get() = when (this) {
        WsButtonSize.Small -> 6.dp
        WsButtonSize.Medium -> 8.dp
        WsButtonSize.Large -> 10.dp
    }
