package com.example.compose.views.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.Routes
import com.example.compose.models.CardData

@Composable
fun Card(cardData : CardData, navController : NavHostController)
{
    val title = stringResource(cardData.titleResource)
    val description = stringResource(cardData.descResource)

    val context = LocalContext.current
    val packageManager = context.packageManager

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column() {
            Row() {
                Image(
                    painter = painterResource(id = cardData.imgResource),
                    contentDescription = "Product Image",
                    modifier = Modifier.padding(all = 15.dp)
                )
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(title)
                    Text(description)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW)
                                context.startActivity(intent)
                            }
                        ) {
                            Text("Share")
                        }

                        Button(
                            onClick = {
                                navController.navigate(Routes.ItemDetail.path)
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
    val cardData = CardData(
        imgResource = R.drawable.ic_launcher_background,
        titleResource = R.string.title_stronghold,
        descResource = R.string.desc_stronghold
    )
    Card(cardData, rememberNavController())
}