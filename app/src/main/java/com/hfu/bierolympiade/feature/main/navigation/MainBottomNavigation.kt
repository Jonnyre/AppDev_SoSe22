package com.hfu.bierolympiade.feature.main.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNavigation(navController: NavController) {
    BottomNavigation (backgroundColor = Color.White){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        listOf(
            BottomNavigationItem.Events,
            BottomNavigationItem.Players,
            BottomNavigationItem.Leaderboard,
        ).forEach { navItem ->
            BottomNavigationItem(

                selected = currentRoute == navItem.routeName,
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = navItem.routeName,
                        modifier = Modifier.size(24.dp))
                },
                selectedContentColor = Color(0xFFF2C299),
                unselectedContentColor = Color.Black,
                onClick = {
                    navController.navigate(navItem.routeName) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
