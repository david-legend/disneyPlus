package com.davidcobbina.disneyplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidcobbina.disneyplus.ui.screens.ChooseProfileScreen
import com.davidcobbina.disneyplus.ui.screens.HomeScreen
import com.davidcobbina.disneyplus.ui.screens.NavigationDrawer
import com.davidcobbina.disneyplus.ui.theme.DisneyPlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyPlusApp()
        }
    }
}

@Composable
fun DisneyPlusApp() {
    DisneyPlusTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            ChooseProfileScreen(modifier = Modifier)
//            HomeScreen()
            NavigationDrawer()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DisneyPlusTheme {
        DisneyPlusApp()
    }
}