package com.fakhri0079.cinerack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fakhri0079.cinerack.ui.screen.DetailScreen
import com.fakhri0079.cinerack.ui.screen.KEY_ID_FILM
import com.fakhri0079.cinerack.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost (
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.NewForm.route){
            DetailScreen(navController)
        }
        composable(
            route = Screen.UpdateForm.route,
            arguments = listOf(
                navArgument(KEY_ID_FILM){type = NavType.LongType}
            )
        ){
            navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_FILM)
            DetailScreen(navController,id)
        }
    }
}