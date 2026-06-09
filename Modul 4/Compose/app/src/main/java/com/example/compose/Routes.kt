package com.example.compose

sealed class Routes(val path : String) {
    object Home : Routes("home")

    object Setting : Routes(path = "setting")
    object ItemDetail : Routes("item_detail/{cardId}"){

        fun createPath(cardId : Int) = "item_detail/$cardId"

    }
}