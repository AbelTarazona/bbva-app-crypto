package com.abeltarazona.bbvacryptowallet.models.balance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AbelTarazona on 24/10/2021
 */
@JsonClass(generateAdapter = true)
data class BalanceRequest(
    @Json(name = "user_id")
    val user_id: String
)
