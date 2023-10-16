package com.example.catviewer.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.catviewer.network.createCatViewerApi
import com.example.catviewer.network.getRandomCatResponse
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var cat by remember { mutableStateOf<getRandomCatResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val catApi = createCatViewerApi()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        cat?.let { CatImage(cat = it) }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                   val response = catApi.getRandomCat()
                    cat = response.body()?.firstOrNull()
                }
                // Call the API to fetch a random cat image

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Random Cat Image")
        }
    }
}


@Composable
fun CatImage(cat: getRandomCatResponse) {
    val imageUrl = cat.url
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .clip(MaterialTheme.shapes.medium)

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )

    Spacer(modifier = Modifier.height(16.dp))
    Text("Width: ${cat.width}")

    Text("Height: ${cat.height}")
}
