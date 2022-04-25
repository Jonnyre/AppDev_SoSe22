package com.hfu.bierolympiade.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hfu.bierolympiade.feature.player.ui.PlayerScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "players") {
        composable(BottomNavigationItem.Players.routeName) {
            PlayerScreen()
        }
        composable(BottomNavigationItem.Cart.routeName) {
            PlayerScreen()
        }
    }
}
