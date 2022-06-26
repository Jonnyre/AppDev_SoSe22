package com.hfu.bierolympiade.feature.eventDetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun GameItem(game: GameUI) {
    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navControllerGlobal?.navigate("gameDetail/${game.id.value}")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(70.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        RsLightOrange
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_gamepad),
                    contentDescription = "Event",
                    modifier = Modifier.size(30.dp)
                )
            }
            Column {
                Text(
                    text = game.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 5.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(all = 5.dp)
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "Status: " + game.status,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameItem_Preview() {
    GameItem(
        GameUI(
            GameId("foo"),
            name = "Flunkyball",
            status = "On Going"
        )
    )
}