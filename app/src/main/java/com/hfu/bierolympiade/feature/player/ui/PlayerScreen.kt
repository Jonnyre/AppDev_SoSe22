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
    val products by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    ProductsScreenUI(products)
}

@Composable
private fun ProductsScreenUI(products: List<PlayerUI>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(products) { product ->
            PlayerItem(product)
        }
    }
}

@Preview
@Composable
fun ProductsScreen_Preview() {
    ProductsScreenUI(emptyList())
}