package com.davidcobbina.disneyplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidcobbina.disneyplus.navigation.SetupNavGraph
import com.davidcobbina.disneyplus.ui.theme.DisneyPlusTheme
import dagger.hilt.android.AndroidEntryPoint


//TODO:: Pass Profile Around
//TODO:: Change profile inside app
//TODO:: Add proper profile avatars
//TODO:: Link proper api's for sections
//TODO:: Hide api key

//Future Impl
//TODO:: Implement watch lists functionality (Add, view and delete watchlist)
//TODO:: Implement downloads functionality (Add, view and delete downloads)
//TODO:: Implement view video functionality
//TODO:: Implement search movies
//TODO:: Implement like a movie
//TODO:: Add tests -> Unit test and UI Tests


// UI Remaining
//TODO: Revisits
//    1. Implement Stacked Downloaded Images (Download Screen)
//    2. Add Gradient Cover to footer of nav drawer
//    2. Add Gradient Cover to footer of movieDetail
//TODO: Search On HomePage
//TODO: Add any missing animations


@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyPlusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

