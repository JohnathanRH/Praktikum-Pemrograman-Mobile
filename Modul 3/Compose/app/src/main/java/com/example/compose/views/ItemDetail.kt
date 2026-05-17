package com.example.compose.views

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Content
import com.example.compose.DummyData
import com.example.compose.R
import com.example.compose.Routes
import com.example.compose.models.CardData
import com.example.compose.views.components.Description
import com.example.compose.views.components.ProductImage
import com.example.compose.views.components.Title

@Composable
fun ItemDetail(cardDataId : Int?, navController : NavController)
{
    val data = DummyData.Card.items[cardDataId ?: 0]
    val title = stringResource(id = data.titleResource)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductImage(
            imgResId = data.imgResource,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Title(stringResource(data.titleResource))
            Description(stringResource(data.descResource))
            Spacer(Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate(route = Routes.Home.path)
                    }
                ) {
                    Text("Home")
                }

                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            data.wikiUri.toUri()
                        )
                        context.startActivity(intent)
                    }
                ){
                    Text("Wiki")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Test(){
    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .padding(innerPadding)
        )
        {
            ItemDetail(0, rememberNavController())
        }
    }
}