package com.hfu.bierolympiade.feature.player.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.PlayerId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun PlayerItem(player: PlayerUI) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .padding(5.dp)
                .clickable {
                    navControllerGlobal?.navigate("addPlayer?playerId=${player.id.value}")
                }
            ,
            shape = CircleShape,
            color = RsLightOrange
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_play_generic),
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
                )
        }
        Text(
            text = player.name,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PlayerItem_Preview() {
    PlayerItem(
        PlayerUI(
            PlayerId("foo"),
            "test",
            "eine test beschreibung",
        )
    )
}