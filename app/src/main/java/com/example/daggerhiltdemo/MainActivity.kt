package com.example.daggerhiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import com.example.daggerhiltdemo.view.HomeView
import com.example.daggermvvmdemo.models.ProductModel
import com.example.daggermvvmdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.productsLiveData?.observe(this) { productsList ->
            var text = productsList.joinToString { x -> x.title + "\n\n" }

            enableEdgeToEdge()
            setContent {
                App(productsList)
            }
        }


    }

    @Composable
    fun App(productsList: List<ProductModel>) {
        var darkTheme = remember { mutableStateOf(value = true) }
        DaggerHiltDemoTheme(darkTheme.value) {
            Surface( // This ensures the background color is applied
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface // Uses the theme's background color
            ) {
                HomeView(productsList as ArrayList, mainViewModel.progress, darkTheme)
            }
        }
    }

    @Preview
    @Composable
    fun AppPreview() {
        App(ArrayList())
    }
}
