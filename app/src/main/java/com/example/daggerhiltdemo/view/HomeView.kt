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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.daggerhiltdemo.R
import com.example.daggerhiltdemo.ui.theme.DaggerHiltDemoTheme
import com.example.daggermvvmdemo.models.ProductModel
import com.example.daggermvvmdemo.viewmodels.MainViewModel

/*
* Created by Parambir Singh ON 2025-02-08
*/

@Preview
@Composable
fun HomePreview() {
    HomeView(viewModel(), remember { mutableStateOf(false) }, {})
}

@Composable
fun HomeView(
    viewmodel: MainViewModel,
    darkThemeState: MutableState<Boolean>,
    onItemClick: () -> Unit
) {
    val products: State<List<ProductModel>> = viewmodel.productsLiveData?.collectAsState()!!
    val showProgressIndicator: State<Boolean> = viewmodel._progress.observeAsState(true)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CustomTopAppBar(false, darkThemeState) {

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(products.value) { items ->
                    Card(colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ), elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp),
                        onClick = {
                            viewmodel.selectedProduct = items
                            onItemClick()
                        }) {
                        ProductRow(items)
                    }
                }
            }
        }
        showProgressIndicator.value.let {
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