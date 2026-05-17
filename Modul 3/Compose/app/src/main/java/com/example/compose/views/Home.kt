package com.example.compose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.DummyData
import com.example.compose.R
import com.example.compose.Routes
import com.example.compose.models.CardData
import com.example.compose.views.components.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController : NavHostController)
{
    val items : List<CardData> = DummyData.Card.items

    Column(){
        IconButton(
            onClick = {navController.navigate(Routes.Setting.path)},
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "setting"
            )
        }
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.size },
            itemWidth = 150.dp
        ) { i ->
            val item = items[i]
            Image(
                modifier = Modifier
                    .height(205.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge),
                painter = painterResource(id = item.imgResource),
                contentDescription = "stuff",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.size(20.dp))

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
}

@Preview(showBackground = true)
@Composable
fun test(){
    Home(navController = rememberNavController())
}