package com.hfu.bierolympiade.feature.eventDetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsButtonBackground
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun EventDetailScreen(viewModel: GameViewModel = viewModel()) {
    val games by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    EventDetailScreenUi(games)
}

@Composable
fun EventDetailScreenUi(games: List<GameUI>) {
    val scrollState = rememberLazyListState()
    Column(
        modifier = Modifier
            .padding(25.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        RsLightOrange
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_blank_event),
                    contentDescription = "Event",
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        Button(
            onClick = { navControllerGlobal?.navigate("leaderboard") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ranking),
                    contentDescription = "leaderboard",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "See Leaderboard", modifier = Modifier.padding(horizontal = 10.dp))
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "gamepad",
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        Text(text = "Games", fontSize = 20.sp)
        LazyColumn(state = scrollState) {
            items(games) { game ->
                GameItem(game)
            }
        }
    }
}

@Preview
@Composable
fun EventDetailScreen_Preview() {
    EventDetailScreenUi(emptyList())
}