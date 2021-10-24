package com.abeltarazona.bbvacryptowallet.models

/**
 * Created by AbelTarazona on 24/10/2021
 */
data class Inversion(
    val img: String,
    val nameLarge: String,
    val nameShort: String,
    val amountCripto: String,
    val amountSoles: String,
) {
    fun getAmountSolesFormatted() = "S/ $amountSoles"
    fun getAmountCriptoFormatted() = "$amountCripto $nameShort"
}
