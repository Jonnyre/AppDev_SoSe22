package com.hfu.bierolympiade.feature.addplayertoevent.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsDarkOrange
import timber.log.Timber
import kotlin.math.log

@Composable
fun AddPlayerToEventScreen(viewModel: AddPlayerToEventViewModel = viewModel()) {

    val players by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    val scrollState = rememberLazyListState()
    var playerList: MutableState<List<String>> = remember { mutableStateOf(emptyList()) }


    Column(
        Modifier
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Choose your participants", fontSize = 20.sp)
        LazyColumn(state = scrollState) {
            items(players) { player ->
                PlayerItem(player, playerList)
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RsDarkOrange,
                contentColor = Color.White
            ),
            onClick = {
                viewModel.addPlayersToEvent(playerList.value);
                Timber.log(Log.INFO, "Alle Spieler")
                var playerListString = ""
                playerList.value.map {
                    playerListString = playerListString.plus("$it;")
                    Timber.log(Log.INFO, it)
                }

                navControllerGlobal?.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("players", playerListString)
                navControllerGlobal?.popBackStack()
        }) {
Text(text = "Save Participants")
        }
    }
}