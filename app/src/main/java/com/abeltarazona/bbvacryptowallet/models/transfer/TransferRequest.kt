package com.abeltarazona.bbvacryptowallet.models.transfer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AbelTarazona on 24/10/2021
 */
@JsonClass(generateAdapter = true)
data class TransferRequest(
    @Json(name = "user_id")
    val user_id: String,
    @Json(name = "amountCoin")
    val amountCoin: String,
    @Json(name = "crypto")
    val crypto: String,
    @Json(name = "amountSoles")
    val amountSoles: String,
)

/**
 * {
"user_id": "cesar.bbva",
"amountCoin": "0.1111",
"crypto": "BITCOIN",
"amountSoles": "100"
}
 */