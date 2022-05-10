package com.hfu.bierolympiade.feature.leaderboard.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.feature.event.ui.EventItem
import com.hfu.bierolympiade.feature.event.ui.EventViewModel

@Composable
fun LeaderboardScreen(viewModel: LeaderboardViewModel = viewModel()) {
    val leaderboardItems by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    LeaderboardScreenUI(leaderBoardItems = leaderboardItems)
}

@Composable
private fun LeaderboardScreenUI(leaderBoardItems: List<LeaderboardUI>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(leaderBoardItems) { leaderboardItem ->
            LeaderboardItem(leaderboardItem, )
        }
    }
}

@Preview
@Composable
fun PlayersScreen_Preview() {
    LeaderboardScreen()
}
