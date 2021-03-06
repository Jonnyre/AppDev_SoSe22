package com.hfu.bierolympiade.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventScreen
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventViewModel
import com.hfu.bierolympiade.feature.addGame.ui.AddGameScreen
import com.hfu.bierolympiade.feature.addGame.ui.AddGameViewModel
import com.hfu.bierolympiade.feature.addPlayer.ui.AddPlayerScreen
import com.hfu.bierolympiade.feature.addPlayer.ui.AddPlayerViewModel
import com.hfu.bierolympiade.feature.addplayertoevent.ui.AddPlayerToEventScreen
import com.hfu.bierolympiade.feature.addplayertoevent.ui.AddPlayerToEventViewModel
import com.hfu.bierolympiade.feature.event.ui.EventScreen
import com.hfu.bierolympiade.feature.event.ui.EventViewModel
import com.hfu.bierolympiade.feature.eventDetail.ui.EventDetailScreen
import com.hfu.bierolympiade.feature.eventDetail.ui.GameViewModel
import com.hfu.bierolympiade.feature.gameDetail.ui.GameDetailScreen
import com.hfu.bierolympiade.feature.gameDetail.ui.GameDetailViewModel
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
        composable("addEvent?eventId={eventId}") {
            val vm = hiltViewModel<AddEventViewModel>()
            AddEventScreen(vm)
        }
        composable("addPlayer?playerId={playerId}") {
            val vm = hiltViewModel<AddPlayerViewModel>()
            AddPlayerScreen(vm)
        }
        composable("eventDetail/{id}") {
            val vm = hiltViewModel<GameViewModel>()
            EventDetailScreen(vm)
        }

        composable("addPlayerToEvent/{id}") {
            val vm = hiltViewModel<AddPlayerToEventViewModel>()
            AddPlayerToEventScreen(vm)
        }

        composable("pickGameType?eventId={eventId}") {
            val vm = hiltViewModel<pickGameTypeViewModel>()
            PickGameTypeScreen(vm)
        }

        composable("addGame?eventId={eventId}&gameTypeId={gameTypeId}&gameId={gameId}") {
            val vm = hiltViewModel<AddGameViewModel>()
            AddGameScreen(vm)
        }

        composable("gameDetail/{id}") {
            val vm = hiltViewModel<GameDetailViewModel>()
            GameDetailScreen(vm)
        }
    }
}
