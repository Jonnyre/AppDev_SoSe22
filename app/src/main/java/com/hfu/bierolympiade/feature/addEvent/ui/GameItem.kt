package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.BierolympiadeTheme
import com.hfu.bierolympiade.ui.theme.RsButtonBackground
import com.hfu.bierolympiade.ui.theme.RsDarkBlue

@Composable
fun GameItem(gameName: String, gameId: GameId, deleteFunction: (gameId: GameId) -> Unit, games: MutableState<List<Game>>) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            painter = painterResource(R.drawable.ic_gamepad),
            contentDescription = "gamepad",
            tint = RsDarkBlue,
            modifier = Modifier
                .size(25.dp)
                .weight(1f)
        )
        Text(
            text = gameName,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.weight(3f)
        )
        Button(
            onClick = {
                navControllerGlobal?.navigate("addGame?eventId=&gameTypeId=&gameId=${gameId.value}")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 5.dp)
        ){
            Icon (
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = "edit",
                tint = RsDarkBlue,
                modifier = Modifier
                    .size(20.dp)
                    .weight(1f)
            )
        }
        Button(
            onClick = {
                games.value = games.value.filterNot { it.id.value == gameId.value }
                deleteFunction(gameId)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 5.dp)
        ){
            Icon (
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "delete",
                tint = RsDarkBlue,
                modifier = Modifier
                    .size(15.dp)
            )
        }

    }
}

@Preview
@Composable
fun GameItem_Preview() {
    BierolympiadeTheme {
        GameItem(
            "Flip Cup",
            GameId("aösfsaöf"),
            {},
            TODO(),
        )
    }
}