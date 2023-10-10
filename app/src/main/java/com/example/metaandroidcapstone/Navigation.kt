package com.example.metaandroidcapstone

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(context: MainActivity, isLoggedIn: Boolean) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = if(isLoggedIn)Home.route else Onboard.route){
        composable(Onboard.route){
            Onboarding(context,navController)
        }
        composable(Home.route){
            Home(context,navController)
        }
        composable(Profile.route){
            Profile(context,navController)
        }
    }
}