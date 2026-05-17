package com.example.compose.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.MainActivity
import com.example.compose.R
import com.example.compose.views.components.Title
import com.multilanguage.LanguageManager

@Composable
fun Setting(navController: NavController)
{
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Title(stringResource(R.string.lang_title))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            TextButton(
                onClick = {changeLocale(context = context, language = "en")},
            ) {
                Text(stringResource(R.string.lang_btn_en))
            }
            TextButton(
                onClick = {changeLocale(context = context, language = "es")},
            ) {
                Text(stringResource(R.string.lang_btn_es))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview()
{
    Setting(rememberNavController())
}

fun changeLocale(context: Context, language: String) {
    LanguageManager.saveLanguage(context, language)
    LanguageManager.setLocale(context, language)
    restartApp(context)
}

fun restartApp(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
    if (context is Activity) {
        context.finish()
    }
}