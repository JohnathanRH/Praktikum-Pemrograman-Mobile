package com.example.tipcalculator

import kotlin.math.roundToInt

data class TipRate(val amount : Float)
{
    val str : String = toString()
    override fun toString(): String {
        val casted = (amount*100f).roundToInt().toString()
        return "$casted%"
    }
}
