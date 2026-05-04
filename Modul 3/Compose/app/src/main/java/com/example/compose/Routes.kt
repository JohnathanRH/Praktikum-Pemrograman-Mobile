package com.example.compose

sealed class Routes(val path : String) {
    object Home : Routes("home")
    object ItemDetail : Routes("item_detail")
}