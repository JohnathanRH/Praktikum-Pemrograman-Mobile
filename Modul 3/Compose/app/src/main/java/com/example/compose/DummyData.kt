package com.example.compose

import com.example.compose.models.CardData

sealed class DummyData(val items : List<CardData>) {
    object Card : DummyData(items = listOf(
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_towerDefenseX,
            descResource = R.string.desc_towerDefenseX
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_foreground,
            titleResource = R.string.title_unknownThreat,
            descResource = R.string.desc_unknownThreat
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_stronghold,
            descResource = R.string.desc_stronghold
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_foreground,
            titleResource = R.string.title_mountAndBlade,
            descResource = R.string.desc_mountAndBlade
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_residentEvil,
            descResource = R.string.desc_residentEvil
        )
    ))
}