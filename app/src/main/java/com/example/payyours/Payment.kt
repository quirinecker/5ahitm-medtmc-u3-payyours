package com.example.payyours

import java.io.Serializable
import java.text.DecimalFormat
import java.util.*

data class Payment(var courts: Int, var players: Int, var pricePerUnitText: String): Serializable {

    val amount: Double
        get() {
            val decimalFormat = DecimalFormat.getInstance(Locale.getDefault()) as DecimalFormat
            val pricePerUnit = decimalFormat.format(pricePerUnitText) ?: return -1.0
            return courts * pricePerUnit.toDouble() / players;
        }

}
