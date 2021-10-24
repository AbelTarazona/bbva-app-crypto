package com.abeltarazona.bbvacryptowallet.repositories

import android.content.Context
import com.abeltarazona.bbvacryptowallet.models.balance.BalanceRequest
import com.abeltarazona.bbvacryptowallet.models.transfer.TransferRequest
import com.abeltarazona.bbvacryptowallet.models.wallet.WalletRequest
import com.abeltarazona.bbvacryptowallet.network.ApiInterface
import com.abeltarazona.bbvacryptowallet.network.SafeApiRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by AbelTarazona on 23/10/2021
 */
class AppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ApiInterface,
) {

    suspend fun createWallet(dni: String) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.createWallet(
                WalletRequest(documentNumber = dni)
            )
        }
    }

    suspend fun getBalance(userId: String) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getBalance(
                BalanceRequest(user_id = userId)
            )
        }
    }

    suspend fun transferBuy(
        userId: String,
        amountCripto: String,
        nameCripto: String,
        amountSoles: String
    ) =
        withContext(Dispatchers.IO) {
            SafeApiRequest.apiRequest(context) {
                api.transferBuy(
                    TransferRequest(userId, amountCripto, nameCripto, amountSoles)
                )
            }
        }


}