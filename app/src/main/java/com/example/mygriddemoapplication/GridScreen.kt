package com.example.mygriddemoapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun GridScreen(modifier: Modifier = Modifier) {
    val images = remember {
        listOf(
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_2,
            R.drawable.img_5,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_5,
            R.drawable.img_3,
            R.drawable.img_1
        ).toMutableStateList()
    }

    var selectedImage by remember { mutableStateOf<Int?>(null) }

    if (selectedImage != null) {
        ImageDetailScreen(
            imageRes = selectedImage!!,
            onBack = { selectedImage = null }
        )
    } else {
        Scaffold(
            modifier = modifier,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("My Grid App") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val randomRes = listOf(
                        R.drawable.img_1,
                        R.drawable.img_2,
                        R.drawable.img_3,
                        R.drawable.img_4,
                        R.drawable.img_5
                    ).random()
                    images.add(randomRes)
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Image")
                }
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(images) { imageRes ->
                    GridImage(
                        imageRes = imageRes,
                        onDeleteImage = { images.remove(imageRes) },
                        onImageClick = { selectedImage = imageRes }
                    )
                }
            }
        }
    }
}

@Composable
private fun GridImage(
    imageRes: Int,
    onDeleteImage: () -> Unit,
    onImageClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onImageClick() }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Item",
            tint = Color.Red,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(10.dp)
                .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                .clickable { onDeleteImage() }
        )
    }
}


@Composable
private fun ImageDetailScreen(imageRes: Int, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Image Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridScreenPreview() {
    GridScreen()
}

