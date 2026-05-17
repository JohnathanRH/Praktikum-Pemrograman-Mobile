package com.example.compose.views.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.Routes
import com.example.compose.models.CardData
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.compose.DummyData

@Composable
fun Card(cardData : CardData, navController : NavHostController)
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
                            onClick = {
                                val route = Routes.ItemDetail.createPath(cardId = cardData.id ?: 0)

                                navController.navigate(
                                    route = route
                                )
                            }
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
    val items : List<CardData> = DummyData.Card.items

    LazyColumn() {
        items(items){ cardData ->
            Card(
                cardData = cardData,
                navController = rememberNavController()
            )
            Spacer(Modifier.padding(bottom = 10.dp))
        }
    }
}