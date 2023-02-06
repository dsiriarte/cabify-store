package com.davidsantiagoiriarte.cabifystore.navigation

sealed class Screen(val route : String){
    object MainScreen : Screen("main_screen")
    object CheckoutScreen : Screen("checkout_screen")
}
