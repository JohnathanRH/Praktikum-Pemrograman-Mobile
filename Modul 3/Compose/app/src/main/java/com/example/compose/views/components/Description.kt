package com.example.compose.views.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Description(text: String, modifier : Modifier? = Modifier){
    Text(
        textAlign = TextAlign.Justify,
        text = text,
        modifier = modifier ?: Modifier
    )
}