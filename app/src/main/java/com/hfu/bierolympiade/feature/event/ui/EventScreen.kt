package com.hfu.bierolympiade.feature.event.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R

@Composable
fun EventScreen(viewModel: EventViewModel = viewModel()) {
    val events by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    EventScreenUI(events)
}

@Composable
private fun EventScreenUI(events: List<EventUI>) {
    val scrollState = rememberLazyListState()
    Scaffold() {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(100.dp)
                        .weight(1.0f)
                        .padding(10.dp)
                )
                Column(
                    modifier = Modifier.weight(2.0f)
                ) {
                    Text(text = "Wednesday, 14 July 2022")
                    Text("Hi Felix!", fontWeight = FontWeight.Bold)
                }
            }
            Text(
                text = "Events",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            LazyColumn(state = scrollState) {
                items(events) { event ->
                    EventItem(event)
                }
            }
        }
    }
}

@Preview
@Composable
fun EventScreen_Preview() {
    EventScreenUI(emptyList())
}