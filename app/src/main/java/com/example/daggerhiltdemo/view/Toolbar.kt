package com.example.daggerhiltdemo.view

import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import com.example.daggerhiltdemo.R

@Preview
@Composable
fun CustomTopBarPreview() {
    CustomTopAppBar(true, remember { mutableStateOf(false) }) {

    }
}

@Composable
fun CustomTopAppBar(
    enableBackButton: Boolean,
    darkThemeState: MutableState<Boolean>,
    onBackPressed: () -> Unit
) {
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
            if (enableBackButton) {
                Icon(
                    painter = painterResource(R.drawable.ic_back), "", Modifier.clickable {
                        onBackPressed()
                    }
                )
            }

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