package com.example.compose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.views.*
import com.example.compose.views.components.NavBar
import com.multilanguage.LanguageManager

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        val language = LanguageManager.getLanguage(newBase)
        val context = LanguageManager.setLocale(newBase, language)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(
                            color = colorResource(R.color.darker_gray)
                        )
                )
                {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content()
{
    val navController : NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.path,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        composable(route = Routes.Home.path){
            Home(navController)
        }
        composable (
            route = Routes.ItemDetail.path,
            arguments = listOf(
                navArgument(name ="cardId"){ type = NavType.IntType }
            )
        ){ arg ->
            ItemDetail(
                cardDataId = arg.arguments?.getInt("cardId"),
                navController = navController
            )
        }
        composable (route = Routes.Setting.path){
            Setting(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        Content()
    }
}