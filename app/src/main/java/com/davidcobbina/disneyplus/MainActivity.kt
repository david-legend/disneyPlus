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
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.MovieDetailScreen
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyPlusApp()
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun DisneyPlusApp() {
    DisneyPlusTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            ChooseProfileScreen(modifier = Modifier)
//            HomeScreen()
//            DownloadScreen()
//            NavigationDrawer()
            MovieDetailScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
fun DefaultPreview() {
    DisneyPlusTheme {
        DisneyPlusApp()
    }
}