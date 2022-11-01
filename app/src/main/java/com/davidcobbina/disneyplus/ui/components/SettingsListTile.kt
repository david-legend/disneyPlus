package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.grey500

@Composable
fun SettingsListTile(
    leading: (@Composable () -> Unit)?,
    trailing: (@Composable () -> Unit)?,
    title: (@Composable () -> Unit)?,
    subtitle: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    horizontalPadding: Dp = 12.dp,
    verticalPadding: Dp = 16.dp,
    leadingAndTitleSpacing: Dp = 8.dp,
    titleSubtitleSpacing: Dp = 8.dp,
    borderRadius: Dp = 12.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {

    Box(
        modifier = modifier
            .fillMaxWidth(1f)
            .background(color = backgroundColor, shape = RoundedCornerShape(borderRadius))
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Row(
            modifier = rowModifier
                .fillMaxWidth(1f)

        ) {
            if (leading != null) {
                leading()
                Spacer(modifier = Modifier.width(leadingAndTitleSpacing))
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                if (title != null) {
                    title()
                }
                if (subtitle != null) {
                    Spacer(modifier = Modifier.width(titleSubtitleSpacing))
                    subtitle()
                }
            }

            if (trailing != null) {
                Spacer(modifier = Modifier.weight(1f))
                trailing()
            }
        }
    }
}

@Composable
fun DefaultSettingsTile(
    title: String,
    subtitle: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(
        fontSize = 18.sp,
        color = Color.White
    ),
    subtitleStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontSize = 16.sp,
        color = Color.White
    ),
    leadingIcon: Painter = painterResource(id = R.drawable.ic_alert_octagon),
    trailingIcon: Painter = painterResource(id = R.drawable.ic_chevron_right),
    trailing: (@Composable () -> Unit)? = null
) {
    SettingsListTile(
        leading = {
            CustomIcon(
                icon = leadingIcon,
                iconPadding = 8.dp,
                iconColor = Color.White,
                iconSize = iconSize
            )
        },
        trailing = if (trailing != null) {
            {
                trailing()
            }
        } else {
            {
                CustomIcon(
                    icon = trailingIcon,
                    iconPadding = 8.dp,
                    iconColor = Color.White,
                    iconSize = iconSize
                )
            }
        },
        title = {
            Text(title, style = titleStyle)
        },
        subtitle = {
            Text(subtitle, style = subtitleStyle)

        }
    )
}