package com.example.daggerhiltdemo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.daggermvvmdemo.models.ProductModel

/*
* Created by Parambir Singh ON 2025-02-09
*/

@Preview
@Composable
fun ProductPhotoPreview() {
    ProductPhotoDialog({}, ProductModel("Category", "Description", 132, "Photo", 234.00, "Title"))
}

@Composable
fun ProductPhotoDialog(
    onDismiss: () -> Unit,
    productModel: ProductModel
) {
    Dialog(onDismiss) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                modifier = Modifier.wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = productModel.image,
                    contentDescription = productModel.description,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(460.dp)
                )

                Icon(
                    painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                    "",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .clickable {
                            onDismiss()
                        },
                )
            }
        }
    }
}