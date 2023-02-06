package com.davidsantiagoiriarte.cabifystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.davidsantiagoiriarte.cabifystore.navigation.Navigation
import com.davidsantiagoiriarte.cabifystore.ui.theme.CabifyStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabifyStoreTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }
}
