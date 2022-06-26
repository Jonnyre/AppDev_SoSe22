package com.hfu.bierolympiade.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.feature.main.navigation.MainBottomNavigation
import com.hfu.bierolympiade.feature.main.navigation.MainNavigationGraph
import com.hfu.bierolympiade.ui.theme.RsDarkOrange

var navControllerGlobal: NavHostController? = null

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    navControllerGlobal = navController
    Scaffold(
        floatingActionButton = {
            if (isScreenWithFloatingAction(currentRoute.value?.destination?.route)) {
                FloatingActionButton(
                    onClick = {
                        when (currentRoute.value?.destination?.route) {
                            "events" -> {
                                navigateToRoute("addEvent")
                            }
                            "players" -> {
                                navigateToRoute("addPlayer")
                            }
                            "leaderboard" -> { /* Action for Screen 3 */
                            }
                        }
                    },
                    backgroundColor = RsDarkOrange,
                    content = {

                        val iconId = when (currentRoute.value?.destination?.route) {
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
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
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

fun navigateToRoute(route: String) {
    navControllerGlobal?.navigate(route){
        launchSingleTop = true
    }
}
