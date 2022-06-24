package com.hfu.bierolympiade.feature.gameDetail.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.TeamId
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun GameDetailScreen(viewModel: GameDetailViewModel = viewModel()) {
    val matches by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    GameDetailScreenUi(matches, viewModel::updateMatchScoreValue)
}

@Composable
fun GameDetailScreenUi(matches: List<MatchUI>, updateMatchScoreValue: (teamId: TeamId, value: Int) -> Unit) {
    val scrollState = rememberLazyListState()
    Column(
        modifier = Modifier
            .padding(10.dp)
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
        LazyColumn(state = scrollState) {
            itemsIndexed(matches) { index, match ->
                MatchItem(match, index + 1, updateMatchScoreValue)
            }
        }
    }
}

@Preview
@Composable
fun GameDetailScreen_Preview() {
    GameDetailScreenUi(emptyList()) { _, _, -> }
}

