package com.example.compose.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.DummyData
import com.example.compose.R
import com.example.compose.models.CardData
import com.example.compose.views.components.Card

@Composable
fun Home(navController : NavHostController)
{
    val items : List<CardData> = DummyData.Card.items

    LazyColumn() {
        items(items){ cardData ->
            Card(
                cardData = cardData,
                navController = navController
            )
            Spacer(Modifier.padding(bottom = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun test(){
    Home(navController = rememberNavController())
}