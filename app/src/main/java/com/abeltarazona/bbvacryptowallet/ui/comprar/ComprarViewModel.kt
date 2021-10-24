package com.abeltarazona.bbvacryptowallet.ui.comprar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abeltarazona.bbvacryptowallet.network.ApiException
import com.abeltarazona.bbvacryptowallet.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AbelTarazona on 24/10/2021
 */
@HiltViewModel
class ComprarViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    // ----------------------------------------------------------------
    private val _eventShowLoading: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowLoading: LiveData<Boolean?>
        get() = _eventShowLoading

    // ----------------------------------------------------------------
    private val _eventShowMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    val eventShowMessage: LiveData<String?>
        get() = _eventShowMessage

    // ----------------------------------------------------------------

    private val _eventShowSuccess: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowSuccess: LiveData<Boolean?>
        get() = _eventShowSuccess

    // ----------------------------------------------------------------

    fun transfer(
        userId: String,
        amountCripto: String,
        nameCripto: String,
        amountSoles: String
    ) = viewModelScope.launch {
        _eventShowLoading.value = true
        try {
            val walletResponse = repository.transferBuy(userId, amountCripto, nameCripto, amountSoles)
            _eventShowSuccess.value = true
        } catch (e: ApiException) {
            _eventShowMessage.value = e.message
            _eventShowSuccess.value = false
        }

        _eventShowLoading.value = false
    }
}