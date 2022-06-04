package com.hfu.bierolympiade.feature.addplayertoevent.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.PlayerId
import com.hfu.bierolympiade.feature.event.ui.EventUI
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsLightOrange

@Composable
fun PlayerItem(player: AddPlayerToEventUI, addedPlayers: MutableState<List<String>>) {
    var isAdded by remember { mutableStateOf(false) }
    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
            ) {
                Row() {
                    Image(
                        painter = painterResource(R.drawable.ic_users),
                        contentDescription = "user",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = player.name)
                }
            }
            IconButton(
                modifier = Modifier.size(25.dp),
                onClick = {
                    isAdded = !isAdded
                    if(isAdded)
                        addedPlayers.value = addedPlayers.value.plus(player.id.value)
                    else
                        addedPlayers.value = addedPlayers.value.filter{it != player.id.value}
                }
            ) {
                if(isAdded)
                Icon(
                    painterResource(R.drawable.ic_close),
                    contentDescription = "add player",
                    tint = RsLightOrange
                )
                else
                    Icon(
                        painterResource(R.drawable.ic_plus),
                        contentDescription = "delete player",
                        tint = RsLightOrange
                    )
            }
        }
    }
}

@Preview
@Composable
fun PlayerItem_Preview() {
    PlayerItem(
        AddPlayerToEventUI(
            PlayerId("foo"),
            "test",

        ),
        remember { mutableStateOf(emptyList()) }
    )
}