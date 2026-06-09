package com.example.compose.views.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.Routes
import com.example.compose.models.CardData
import androidx.core.net.toUri
import com.example.compose.CardUiState

@Composable
fun Card(cardData : CardData, onClick: () -> Unit)
{
    val title = stringResource(cardData.titleResource)
    val description = stringResource(cardData.descResource)

    val context = LocalContext.current
    val packageManager = context.packageManager

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = colorResource(R.color.Gray),
                shape = RoundedCornerShape(10.dp)
            )
    ){
        Column() {
            Row() {
                ProductImage(
                    imgResId = cardData.imgResource,
                    modifier = Modifier
                        .padding(all = 15.dp)
                        .fillMaxHeight()
                        .width(100.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Title(
                        text = title,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Description(text = description)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, cardData.wikiUri.toUri())
                                context.startActivity(intent)
                            }
                        ) {
                            Text("Wiki")
                        }

                        Button(
                            onClick = onClick
                        ){
                            Text("Detail")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test()
{
//    val items : List<CardData> = CardUiState.Card.items
//
//    LazyColumn() {
//        items(items){ cardData ->
//            Card(
//                cardData = cardData,
//                navController = rememberNavController()
//            )
//            Spacer(Modifier.padding(bottom = 10.dp))
//        }
//    }
}