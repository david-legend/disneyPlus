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


//TODO: Adaptive Layouts on All Screens
//    1. Fine tune home screen adaptiveness --> Done
//    1. Fine tune menu screen adaptiveness
//    1. Fine tune download screen adaptiveness
//    1. Add proper images for homeScreen
//    1. Add proper images for Download Screen
//    1. Fine tune movieDetail screen


//TODO:: Passing Data Around



//Next Phase
//TODO:: Implement Onboarding Screen - Draw Inspiration from Netflix
//TODO:: Implement Login/SignUp Screen - Draw Inspiration from Netflix
//TODO:: Implement Add User Screen - Draw Inspiration from Netflix



// After completing all screens
//TODO: Revisits
//    1. Implement Stacked Downloaded Images (Download Screen)
//    2. Add Gradient Cover to footer of nav drawer
//    2. Add Gradient Cover to footer of movieDetail
//TODO: Search On HomePage
//TODO: Add any missing animations


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

