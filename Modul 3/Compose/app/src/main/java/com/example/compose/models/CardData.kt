package com.example.compose.models

data class CardData(
    val id : Int?,
    val imgResource : Int,
    val titleResource : Int,
    val descResource : Int,
    val wikiUri : String
) { }