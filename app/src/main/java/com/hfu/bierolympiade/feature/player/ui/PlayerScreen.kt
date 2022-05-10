package com.hfu.bierolympiade.feature.player.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun PlayerScreen(viewModel: PlayersViewModel = viewModel()) {
    val players by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    PlayersScreenUI(players)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PlayersScreenUI(players: List<PlayerUI>) {
    Column (
    ){
        Text(
            text = "Players",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp),
        ){
            items(players) { player ->
                PlayerItem(player)
            }
        }
    }
}

@Preview
@Composable
fun PlayersScreen_Preview() {
    PlayersScreenUI(emptyList())
}