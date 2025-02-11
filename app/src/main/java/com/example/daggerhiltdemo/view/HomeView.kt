package com.example.daggerhiltdemo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImage
import com.example.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import com.example.daggermvvmdemo.models.ProductModel

/*
* Created by Parambir Singh ON 2025-02-08
*/
@Composable
fun HomeView(products: ArrayList<ProductModel>, showProgressIndicator: LiveData<Boolean>) {

    // State to manage which product's details to show
    var selectedProduct = remember { mutableStateOf<ProductModel?>(null) }
    var showDialog = remember { mutableStateOf(false) }

    val showProgress = showProgressIndicator.observeAsState()

    // Show dialog if a product is selected
    if (showDialog.value && selectedProduct != null) {
        ProductDescriptionDialog(
            onDismiss = { showDialog.value = false },
            productModel = selectedProduct.value!!
        )
    }

    DaggerHiltDemoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                CustomTopAppBar()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    var index = 0
                    products.forEach { message ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ), modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp),
                            onClick = {
                                selectedProduct.value = message
                                showDialog.value = true
                            }
                        ) {
                            ProductRow(message)
                        }
                        index++
                    }
                }
            }
            showProgress.value?.let {
                if (it)
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(64.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeWidth = 8.dp
                    )
            }
        }
    }
}


@Composable
fun ProductRow(productModel: ProductModel) {
    Row(modifier = Modifier.padding(10.dp)) {
        AsyncImage(
            model = productModel.image, contentDescription = "Image description", modifier = Modifier
                .height(70.dp)
                .width(70.dp)
                .padding(end = 10.dp)
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "$${productModel.price}",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = productModel.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Black
            )
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name, modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerHiltDemoTheme {
        Greeting("Android")
    }
}

@Composable
fun CustomTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "MVVM with Hilt", style = MaterialTheme.typography.labelLarge, modifier = Modifier
                .weight(1f)
                .padding(10.dp), color = Color.White
        )
        IconButton(onClick = { /* Handle action */ }) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
    }
}