package com.design.workshop.expressive.ui.components.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.design.workshop.expressive.ui.theme.WorkshopThemeTokens
import com.design.workshop.expressive.ui.theme.WsColors

/**
 * Interactive Atlas-style search input surface.
 *
 * The default leading content is a text magnifier so the field works on all KMP targets without
 * requiring platform icon resources. Consumers can pass any supported icon pack through
 * an `Icon(imageVector = ..., ...)` as [leadingIcon].
 */
@Composable
fun WsSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
    containerColor: Color = if (MaterialTheme.colorScheme.background == WsColors.DarkBackground) {
        WsColors.DarkCodeBackground
    } else {
        WsColors.LightCodeBackground
    },
) {
    val shape = RoundedCornerShape(WorkshopThemeTokens.radius.lg)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(shape)
            .background(containerColor, shape)
            .border(BorderStroke(WorkshopThemeTokens.border.thin, MaterialTheme.colorScheme.outline), shape)
            .padding(horizontal = WorkshopThemeTokens.spacing.lg),
        enabled = enabled,
        singleLine = true,
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(WorkshopThemeTokens.spacing.sm),
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                } else {
                    Text(
                        text = "⌕",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    innerTextField()
                }
            }
        },
    )
}
