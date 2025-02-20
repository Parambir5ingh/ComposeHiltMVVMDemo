package com.example.daggerhiltdemo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import com.example.daggerhiltdemo.R
import com.example.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import com.example.daggermvvmdemo.models.ProductModel

/*
* Created by Parambir Singh ON 2025-02-08
*/

@Preview
@Composable
fun HomePreview() {
    HomeView(ArrayList(), MutableLiveData<Boolean>(), remember { mutableStateOf(false) })
}

@Composable
fun HomeView(
    products: ArrayList<ProductModel>,
    showProgressIndicator: LiveData<Boolean>,
    darkThemeState: MutableState<Boolean>
) {

    // State to manage which product's details to show
    var selectedProduct = remember { mutableStateOf<ProductModel?>(null) }
    var showDialog = remember { mutableStateOf(false) }

    val showProgress = showProgressIndicator.observeAsState()

    // Show dialog if a product is selected
    if (showDialog.value && selectedProduct != null) {
        ProductDescriptionDialog(
            onDismiss = { showDialog.value = false }, productModel = selectedProduct.value!!
        )
    }

    DaggerHiltDemoTheme(darkThemeState.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CustomTopAppBar(darkThemeState)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(products) { items ->
                        Card(colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ), elevation = CardDefaults.cardElevation(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp),
                            onClick = {
                                selectedProduct.value = items
                                showDialog.value = true
                            }) {
                            ProductRow(items)
                        }
                    }
                }
            }
            showProgress.value?.let {
                if (it) CircularProgressIndicator(
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
            model = productModel.image,
            contentDescription = "Image description",
            modifier = Modifier
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
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = productModel.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Composable
fun CustomTopAppBar(darkThemeState: MutableState<Boolean>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp), // Adds elevation for a modern look
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MVVM with Hilt",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f),
            )

            IconButton(onClick = { darkThemeState.value = !darkThemeState.value }) {
                Icon(
                    painter = painterResource(if (darkThemeState.value) R.drawable.ic_dark_mode else R.drawable.ic_light_mode),
                    contentDescription = "Toggle Theme",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }


}