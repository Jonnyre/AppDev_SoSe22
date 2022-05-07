package com.hfu.bierolympiade.feature.player.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun PlayerScreen(viewModel: PlayersViewModel = viewModel()) {
    val players by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    PlayersScreenUI(players)
}

@Composable
private fun PlayersScreenUI(players: List<PlayerUI>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(players) { player ->
            PlayerItem(player)
        }
    }
}

@Preview
@Composable
fun PlayersScreen_Preview() {
    PlayersScreenUI(emptyList())
}