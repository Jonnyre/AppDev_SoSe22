package com.hfu.bierolympiade.feature.main.navigation

import com.hfu.bierolympiade.R


sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Events : BottomNavigationItem() {
        override val routeName = "events"
        override val title = R.string.events_title_navigation
        override val icon = R.drawable.ic_home
    }

    object Players : BottomNavigationItem() {
        override val routeName = "players"
        override val title = R.string.players_title_navigation
        override val icon = R.drawable.ic_users
    }

    object Leaderboard : BottomNavigationItem() {
        override val routeName = "leaderboard"
        override val title = R.string.leaderboard_title_navigation
        override val icon = R.drawable.ic_ranking
    }
}
