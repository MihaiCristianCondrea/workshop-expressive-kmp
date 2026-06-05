package com.design.workshop.expressive.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.util.bounceClick

enum class WsButtonVariant {
    Primary,
    Secondary,
    Tertiary,
    Ghost,
}

enum class WsButtonSize {
    Small,
    Medium,
    Large,
}

@Composable
fun WsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: WsButtonVariant = WsButtonVariant.Primary,
    size: WsButtonSize = WsButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val height = size.height
    val horizontalPadding = size.horizontalPadding
    val buttonEnabled = enabled && !loading

    val content: @Composable RowScope.() -> Unit = {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.defaultMinSize(minWidth = 18.dp, minHeight = 18.dp),
                strokeWidth = 2.dp,
                color = if (variant == WsButtonVariant.Primary) Color.White else MaterialTheme.colorScheme.primary,
            )
        } else {
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

    when (variant) {
        WsButtonVariant.Primary -> Button(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = modifier.heightIn(min = height).bounceClick(),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
            ),
            content = content,
        )

        WsButtonVariant.Secondary -> Button(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = modifier.heightIn(min = height).bounceClick(),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colorScheme.primary,
            ),
            content = content,
        )

        WsButtonVariant.Tertiary -> OutlinedButton(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = modifier.heightIn(min = height).bounceClick(),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            ),
            interactionSource = interactionSource,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            content = content,
        )

        WsButtonVariant.Ghost -> OutlinedButton(
            onClick = onClick,
            enabled = buttonEnabled,
            modifier = modifier.heightIn(min = height).bounceClick(),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            border = null,
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
