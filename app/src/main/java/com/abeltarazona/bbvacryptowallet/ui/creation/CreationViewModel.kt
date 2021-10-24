package com.abeltarazona.bbvacryptowallet.ui.creation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abeltarazona.bbvacryptowallet.models.wallet.Wallet
import com.abeltarazona.bbvacryptowallet.network.ApiException
import com.abeltarazona.bbvacryptowallet.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AbelTarazona on 23/10/2021
 */

@HiltViewModel
class CreationViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _eventShowMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    val eventShowMessage: LiveData<String?>
        get() = _eventShowMessage

    // ----------------------------------------------------------------

    private val _wallet: MutableLiveData<Wallet> by lazy {
        MutableLiveData<Wallet>()
    }

    val wallet: LiveData<Wallet?>
        get() = _wallet

    // ----------------------------------------------------------------
    private val _eventShowLoading: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowLoading: LiveData<Boolean?>
        get() = _eventShowLoading

    // ----------------------------------------------------------------

    private val _eventShowSuccess: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowSuccess: LiveData<Boolean?>
        get() = _eventShowSuccess

    // ----------------------------------------------------------------

    fun createWallet(dni: String) = viewModelScope.launch {
        _eventShowLoading.value = true
        try {
            val walletResponse = repository.createWallet(dni)

            _wallet.value = walletResponse.data[0]
            _eventShowSuccess.value = true
        } catch (e: ApiException) {
            _eventShowMessage.value = e.message
            _eventShowSuccess.value = false
        }

        _eventShowLoading.value = false
    }

}