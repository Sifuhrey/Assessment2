package com.fakhri0079.cinerack.navigation

sealed class Screen(val route : String) {
    data object Home: Screen("mainScreen")
}