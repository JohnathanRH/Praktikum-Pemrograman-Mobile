package com.example.compose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Routes
import com.example.compose.viewmodel.GameViewModelFactory
import com.example.compose.viewmodel.GamesViewModel
import com.example.compose.viewmodel.NavigationEvent
import com.example.compose.views.components.Card
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    val factory: GameViewModelFactory = remember {
        GameViewModelFactory(username = "Admin")
    }
    val viewModel: GamesViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> navController.navigate(event.route)
                is NavigationEvent.NavigateBack -> navController.popBackStack()
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Welcome back,",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = uiState.username ?: "Username",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { viewModel.onClicked(Routes.Setting.path) }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        HorizontalUncontainedCarousel(
            state = rememberCarouselState { uiState.items.size },
            itemWidth = 150.dp
        ) { i ->
            val item = uiState.items[i]
            Image(
                modifier = Modifier
                    .height(205.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge),
                painter = painterResource(id = item.imgResource),
                contentDescription = "Carousel Item",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn {
            items(uiState.items) { cardData ->
                Timber.d("Data game: ${stringResource(cardData.titleResource)}")
                Card(
                    cardData = cardData,
                    onClick = {
                        Timber.d("Data game lengkap: ${cardData}")
                        viewModel.onDetailClicked(cardData.id ?: 0)
                    }
                )
                Spacer(Modifier.padding(bottom = 10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
//    Home(navController = rememberNavController())
}