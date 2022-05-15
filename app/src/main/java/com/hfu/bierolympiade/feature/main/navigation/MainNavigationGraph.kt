package com.hfu.bierolympiade.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventScreen
import com.hfu.bierolympiade.feature.addPlayer.ui.AddPlayerScreen
import com.hfu.bierolympiade.feature.event.ui.EventScreen
import com.hfu.bierolympiade.feature.eventDetail.ui.EventDetailScreen
import com.hfu.bierolympiade.feature.leaderboard.ui.LeaderboardScreen
import com.hfu.bierolympiade.feature.player.ui.PlayerScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "events") {
        composable(BottomNavigationItem.Events.routeName) {
            EventScreen()
        }
        composable(BottomNavigationItem.Players.routeName) {
            PlayerScreen()
        }
        composable(BottomNavigationItem.Leaderboard.routeName) {
            LeaderboardScreen()
        }
        composable("addEvent") {
            AddEventScreen()
        }
        composable("addPlayer") {
            AddPlayerScreen()
        }
        composable("eventDetail") {
            EventDetailScreen()
        }
    }
}
