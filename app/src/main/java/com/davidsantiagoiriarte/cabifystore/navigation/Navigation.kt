package com.davidsantiagoiriarte.cabifystore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.davidsantiagoiriarte.cabifystore.views.checkoutscreen.CheckoutScreen
import com.davidsantiagoiriarte.cabifystore.views.mainscreen.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.CheckoutScreen.route)
        {
            CheckoutScreen(navController = navController)
        }
    }
}
