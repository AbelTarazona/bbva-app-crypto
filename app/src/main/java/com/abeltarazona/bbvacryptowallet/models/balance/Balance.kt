package com.abeltarazona.bbvacryptowallet.models.balance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AbelTarazona on 24/10/2021
 */
@JsonClass(generateAdapter = true)
data class Balance(
    @Json(name = "BBTC")
    val bbtc: Double,
    @Json(name = "BETH")
    val beth: Double,
    @Json(name = "BBVA")
    val bbva: Double,
    @Json(name = "ADA")
    val ada: Double,
    @Json(name = "COD")
    val cod: Double,
    @Json(name = "BBVATokens")
    val tokens: Int,
    @Json(name = "saldoSoles")
    val saldoSoles: Double
)
