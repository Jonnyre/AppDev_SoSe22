package com.hfu.bierolympiade.feature.leaderboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.domain.model.EventId

@Composable
fun LeaderboardScreen(viewModel: LeaderboardViewModel = viewModel()) {
    val events by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    val leaderBoardItems by viewModel.leaderBoardItems.observeAsState(emptyList())
    LeaderboardScreenUI(events, leaderBoardItems, viewModel::getLeaderBoardForEvent)
}

@Composable
private fun LeaderboardScreenUI(
    events: List<LeaderboardUI>,
    leaderBoardItems: List<LeaderBoardItemUI>,
    getLeaderBoardForEvent: (eventId: EventId) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Leaderboard",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )
            Box() {
                Text(
                    "Choose Event",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = true }
                        )
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    events.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded = false
                            getLeaderBoardForEvent(s.eventId)
                        }) {
                            Text(text = s.name)
                        }
                    }
                }
            }

        }

        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            itemsIndexed(leaderBoardItems) { index, leaderboardItem ->
                LeaderboardItem(leaderboardItem, index + 1)
            }
        }
    }
}

@Preview
@Composable
fun LeaderboardScreen_Preview() {
    LeaderboardScreenUI(emptyList(), emptyList()) { _ -> }
}
