package com.example.daggerhiltdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltdemo.view.HomeView
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
                HomeView(productsList as ArrayList, mainViewModel.progress)
            }
        }


    }

    @Preview
    @Composable
    fun homePreview() {
        HomeView(java.util.ArrayList(), mainViewModel.progress)
    }
}
