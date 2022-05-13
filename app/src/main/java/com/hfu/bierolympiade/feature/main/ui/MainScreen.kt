package com.hfu.bierolympiade.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.feature.main.navigation.BottomNavigationItem
import com.hfu.bierolympiade.feature.main.navigation.MainBottomNavigation
import com.hfu.bierolympiade.feature.main.navigation.MainNavigationGraph


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    Scaffold(
        floatingActionButton = {
            if (isScreenWithFloatingAction(currentRoute.value?.destination?.route)) {
                FloatingActionButton(
                    onClick = {
                        when (currentRoute.value?.destination?.route) {
                            "events" -> {
                                navController.navigate("addEvent") {
                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            "players" -> { /* Action for Screen 2 */ }
                            "leaderboard" -> { /* Action for Screen 3 */ }
                        }
                    },
                    backgroundColor = Color(0xFFEC814C),
                    content = {

                        val iconId = when(currentRoute.value?.destination?.route) {
                            "events" -> R.drawable.ic_plus
                            "players" -> R.drawable.ic_plus
                            "leaderboard" -> R.drawable.ic_refresh
                            else -> R.drawable.ic_plus
                        }
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )
            } else null
        },
        bottomBar = { MainBottomNavigation(navController) }
    ) {
        Box() {
            MainNavigationGraph(navController)
        }
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}

fun isScreenWithFloatingAction(route: String?): Boolean {
    return when (route) {
        "events", "players", "leaderboard" -> true
        else -> false
    }
}
