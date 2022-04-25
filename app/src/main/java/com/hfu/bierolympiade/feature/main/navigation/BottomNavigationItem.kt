package com.hfu.bierolympiade.feature.main.navigation

import com.hfu.bierolympiade.R


sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Players : BottomNavigationItem() {
        override val routeName = "players"
        override val title = R.string.players_title_navigation
        override val icon = R.drawable.ic_restaurant_white_24dp
    }

    object Cart : BottomNavigationItem() {
        override val title = R.string.cart_title_navigation
        override val icon = R.drawable.ic_baseline_shopping_cart_24
        override val routeName = "cart"
    }
}
