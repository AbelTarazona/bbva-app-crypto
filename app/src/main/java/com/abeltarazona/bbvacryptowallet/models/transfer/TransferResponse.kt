package com.abeltarazona.bbvacryptowallet.models.transfer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AbelTarazona on 24/10/2021
 */
@JsonClass(generateAdapter = true)
data class TransferResponse(
    @Json(name = "message")
    val message: String,
)
