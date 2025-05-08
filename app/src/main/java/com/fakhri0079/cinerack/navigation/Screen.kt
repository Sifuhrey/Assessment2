package com.fakhri0079.cinerack.navigation

import com.fakhri0079.cinerack.ui.screen.KEY_ID_FILM

sealed class Screen(val route : String) {
    data object Home: Screen("mainScreen")
    data object NewForm: Screen("detailScreen")
    data object UpdateForm: Screen("detailScreen/{$KEY_ID_FILM}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}