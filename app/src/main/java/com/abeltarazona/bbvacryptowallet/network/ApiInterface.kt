package com.abeltarazona.bbvacryptowallet.network

import com.abeltarazona.bbvacryptowallet.models.balance.BalanceRequest
import com.abeltarazona.bbvacryptowallet.models.balance.BalanceResponse
import com.abeltarazona.bbvacryptowallet.models.transfer.TransferRequest
import com.abeltarazona.bbvacryptowallet.models.transfer.TransferResponse
import com.abeltarazona.bbvacryptowallet.models.wallet.WalletRequest
import com.abeltarazona.bbvacryptowallet.models.wallet.WalletResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiInterface {

    @PATCH("wallet")
    suspend fun createWallet(@Body request: WalletRequest): Response<WalletResponse>

    @POST("wallet/getBalance")
    suspend fun getBalance(@Body request: BalanceRequest): Response<BalanceResponse>

    @POST("wallet/transferBuy")
    suspend fun transferBuy(@Body request: TransferRequest): Response<TransferResponse>
}
