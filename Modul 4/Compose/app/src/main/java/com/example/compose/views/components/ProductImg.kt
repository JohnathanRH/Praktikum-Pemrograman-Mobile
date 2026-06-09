package com.example.compose.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductImage(imgResId: Int, modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = imgResId),
        contentDescription = "Product Image",
        contentScale = ContentScale.Crop,
        modifier = modifier.
            clip(RoundedCornerShape(16.dp))
    )
}