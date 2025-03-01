package com.example.daggerhiltdemo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.daggermvvmdemo.viewmodels.MainViewModel

@Preview
@Composable
fun ProductDescriptionPreview() {
    ProductDescriptionView(remember { mutableStateOf(false) }, viewModel()) { }
}

@Composable
fun ProductDescriptionView(
    darkThemeState: MutableState<Boolean>,
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val productModel = viewModel.selectedProduct

    // State to manage which product's details to show
    var showDialog = remember { mutableStateOf(false) }

    // Show dialog if a product is selected
    if (showDialog.value && productModel != null) {
        ProductPhotoDialog(
            onDismiss = { showDialog.value = false }, productModel = productModel
        )
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        CustomTopAppBar(true, darkThemeState) {
            onDismiss()
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                AsyncImage(
                    model = productModel?.image,
                    contentDescription = productModel?.description,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                        .clickable {
                            showDialog.value = true
                        }
                )
                Text(
                    text = productModel?.description!!,
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}