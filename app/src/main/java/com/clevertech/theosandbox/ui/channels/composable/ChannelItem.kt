package com.ballyscorp.ballylive.ui.social.channels.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ballyscorp.ballylive.ui.common.theme.BallysRippleTheme
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.clevertech.theosandbox.R
import com.clevertech.theosandbox.ui.Channel
import com.clevertech.theosandbox.ui.UIChannelItem

@Composable
fun ChannelItem(
    modifier: Modifier = Modifier,
    item: UIChannelItem,
    onClick: (UIChannelItem) -> Unit,
) {
    val backgroundColor = if (item.isSelected) {
        BallysTheme.colors.terrain.hue.purple5
    } else {
        BallysTheme.colors.terrain.white
    }
    BallysRippleTheme {
        Row(
            verticalAlignment = CenterVertically,
            modifier = modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable { onClick(item) }
        ) {
            if (item.isSelected) {
                Divider(
                    color = BallysTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp)
                )
            }
            AsyncImage(
                model = item.channelDetail.logo,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(width = 48.dp, height = 25.dp)
                    .clip(shape = RoundedCornerShape(size = 3.dp))
            )
            ChannelBasicInfo(
                onAirTime = item.onAirTime,
                programName = item.currentProgramName ?: stringResource(id = R.string.coming_up_next),
                isLive = item.isLive,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, end = 12.dp)
            )
            Divider(
                color = BallysTheme.colors.terrain.hue.purple4,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Preview()
@Composable
fun ChannelItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ChannelItem(
            item = UIChannelItem(
                channelDetail = Channel(
                    "https://assets-stratosphere.ballys.tv/images/milb1.png",
                    "https://assets-stratosphere.ballys.tv/images/milb1.png",
                    "MiLB 1",
                    1,
                    1,
                    pubnubChannelName = "public.name",
                    adPreRollTag = null,
                    adMidRollTag = null
                ),
                currentProgramName = "Test program",
                onAirTime = "12:00PM-4:00PM",
                isLive = true,
                pubnubChannelName = "public.name",
                adTag = null,
                isSelected = true
            ),
            onClick = {},
        )
    }
}
