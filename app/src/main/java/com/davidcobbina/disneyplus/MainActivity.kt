package com.davidcobbina.disneyplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidcobbina.disneyplus.navigation.SetupNavGraph
import com.davidcobbina.disneyplus.ui.screens.SelectAccountScreen
import com.davidcobbina.disneyplus.ui.theme.DisneyPlusTheme


//TODO: Movie Detail
//TODO: Link Pages Together (Navigation In Compose)
//TODO: Adaptive Layouts

//TODO: Search On HomePage

//TODO: Add any missing animations

// After completing all screens
//TODO: Fix app screens for landscape mode
//TODO: Revisits
//    1. Implement Stacked Downloaded Images
//    2. Add Gradient Cover to footer of nav drawer


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

