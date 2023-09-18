package com.ballyscorp.ballylive.ui.social.channels.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.clevertech.theosandbox.R

@Composable
fun ChannelBasicInfo(
    modifier: Modifier = Modifier,
    onAirTime: String,
    programName: String,
    isLive: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (onAirTime.isNotEmpty()) {
            Text(
                text = onAirTime,
                color = BallysTheme.colors.terrain.hue.purple1,
                style = BallysTheme.typography.small
            )
        }
        Row(modifier = Modifier.padding(top = 4.dp)) {
            if (isLive) {
                Icon(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .align(CenterVertically),
                    painter = painterResource(id = R.drawable.ic_broadcast),
                    contentDescription = null,
                    tint = BallysTheme.colors.primary
                )
            }
            Text(
                text = programName,
                color = BallysTheme.colors.terrain.hue.purple1,
                style = BallysTheme.typography.subtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun ChannelBasicInfoPreview() {
    ChannelBasicInfo(
        onAirTime = "12:00PM-4:00PM",
        programName = "MiLB Highlights",
        isLive = true,
    )
}
