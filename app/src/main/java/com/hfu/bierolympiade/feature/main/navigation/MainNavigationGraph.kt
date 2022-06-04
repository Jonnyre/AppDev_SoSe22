package com.hfu.bierolympiade.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventScreen
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventViewModel
import com.hfu.bierolympiade.feature.addPlayer.ui.AddPlayerScreen
import com.hfu.bierolympiade.feature.addPlayer.ui.AddPlayerViewModel
import com.hfu.bierolympiade.feature.addplayertoevent.ui.AddPlayerToEventScreen
import com.hfu.bierolympiade.feature.addplayertoevent.ui.AddPlayerToEventViewModel
import com.hfu.bierolympiade.feature.event.ui.EventScreen
import com.hfu.bierolympiade.feature.event.ui.EventViewModel
import com.hfu.bierolympiade.feature.eventDetail.ui.EventDetailScreen
import com.hfu.bierolympiade.feature.eventDetail.ui.GameViewModel
import com.hfu.bierolympiade.feature.leaderboard.ui.LeaderboardScreen
import com.hfu.bierolympiade.feature.leaderboard.ui.LeaderboardViewModel
import com.hfu.bierolympiade.feature.pickGameType.ui.PickGameTypeScreen
import com.hfu.bierolympiade.feature.pickGameType.ui.pickGameTypeViewModel
import com.hfu.bierolympiade.feature.player.ui.PlayerScreen
import com.hfu.bierolympiade.feature.player.ui.PlayersViewModel

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "events") {
        composable(BottomNavigationItem.Events.routeName) {
            val vm = hiltViewModel<EventViewModel>()
            EventScreen(vm)
        }
        composable(BottomNavigationItem.Players.routeName) {
            val vm = hiltViewModel<PlayersViewModel>()
            PlayerScreen(vm)
        }
        composable(BottomNavigationItem.Leaderboard.routeName) {
            val vm = hiltViewModel<LeaderboardViewModel>()
            LeaderboardScreen(vm)
        }
        composable("addEvent") {
            val vm = hiltViewModel<AddEventViewModel>()
            AddEventScreen(vm)
        }
        composable("addPlayer") {
            val vm = hiltViewModel<AddPlayerViewModel>()
            AddPlayerScreen(vm)
        }
        composable("eventDetail/{id}") {
            val vm = hiltViewModel<GameViewModel>()
            EventDetailScreen(vm)
        }

        composable("addPlayerToEvent") {
            val vm = hiltViewModel<AddPlayerToEventViewModel>()
            AddPlayerToEventScreen(vm)
        }

        composable("pickGameType") {
            val vm = hiltViewModel<pickGameTypeViewModel>()
            PickGameTypeScreen(vm)
        }
    }
}
