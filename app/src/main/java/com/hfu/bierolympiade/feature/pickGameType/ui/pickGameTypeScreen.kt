package com.hfu.bierolympiade.feature.pickGameType.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PickGameTypeScreen(viewModel: pickGameTypeViewModel = viewModel()) {
    val gameTypeItems by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    PickGameTypeUI(gameTypeItems)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PickGameTypeUI(gameTypeItems: List<pickGameTypeUI>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(gameTypeItems) { item ->
            PickGameTypeItem(item)
        }
    }
}

@Preview
@Composable
fun AddEventScreen_Preview() {
    PickGameTypeScreen()
}
