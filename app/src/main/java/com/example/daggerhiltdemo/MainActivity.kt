package com.example.daggerhiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import com.example.daggerhiltdemo.view.HomeView
import com.example.daggerhiltdemo.view.ProductDescriptionView
import com.example.daggermvvmdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation() {
        val mainViewModel: MainViewModel = viewModel()
        val navController = rememberNavController()

        var darkTheme = remember { mutableStateOf(value = true) }
        DaggerHiltDemoTheme(darkTheme.value) {
            Surface( // This ensures the background color is applied
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface // Uses the theme's background color
            ) {
                NavHost(navController, startDestination = "main") {
                    composable(route = "main") {
                        HomeView (mainViewModel, darkTheme) {
                            navController.navigate("description")
                        }
                    }
                    composable(route = "description") {
                        ProductDescriptionView(darkTheme, mainViewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun AppPreview() {
        HomeView (viewModel(), remember { mutableStateOf(false) }) {

        }
    }
}
