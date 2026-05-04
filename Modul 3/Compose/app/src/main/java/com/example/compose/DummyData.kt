package com.example.compose

import com.example.compose.models.CardData

sealed class DummyData(val items : List<CardData>) {
    object Card : DummyData(items = listOf(
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_towerDefenseX,
            descResource = R.string.desc_towerDefenseX,
            wikiUri = "https://tdx.fandom.com/wiki/Tower_Defense_X_Wiki"
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_foreground,
            titleResource = R.string.title_unknownThreat,
            descResource = R.string.desc_unknownThreat,
            wikiUri = "https://unknownthreat.fandom.com/wiki/Unknown_Threat_Wiki"
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_stronghold,
            descResource = R.string.desc_stronghold,
            wikiUri = "https://stronghold.fandom.com/wiki/Stronghold_Crusader"
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_foreground,
            titleResource = R.string.title_mountAndBlade,
            descResource = R.string.desc_mountAndBlade,
            wikiUri = "https://mountandblade.fandom.com/wiki/Mount%26Blade:_Warband"
        ),
        CardData(
            imgResource = R.drawable.ic_launcher_background,
            titleResource = R.string.title_residentEvil,
            descResource = R.string.desc_residentEvil,
            wikiUri = "https://residentevil.fandom.com/wiki/Resident_Evil_franchise"
        )
    ))
}