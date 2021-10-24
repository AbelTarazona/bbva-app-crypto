package com.abeltarazona.bbvacryptowallet.models.wallet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AbelTarazona on 23/10/2021
 */
@JsonClass(generateAdapter = true)
data class WalletRequest(
    @Json(name = "documentNumber")
    val documentNumber: String
)
