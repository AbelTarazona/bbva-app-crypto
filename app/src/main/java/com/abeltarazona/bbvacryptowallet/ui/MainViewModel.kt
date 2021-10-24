package com.abeltarazona.bbvacryptowallet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abeltarazona.bbvacryptowallet.models.balance.Balance
import com.abeltarazona.bbvacryptowallet.network.ApiException
import com.abeltarazona.bbvacryptowallet.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AbelTarazona on 24/10/2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    // ----------------------------------------------------------------
    private val _eventShowLoading: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventShowLoading: LiveData<Boolean?>
        get() = _eventShowLoading

    // ----------------------------------------------------------------

    private val _balance: MutableLiveData<Balance> by lazy {
        MutableLiveData<Balance>()
    }

    val balance: LiveData<Balance?>
        get() = _balance

    // ----------------------------------------------------------------
    private val _eventShowMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    val eventShowMessage: LiveData<String?>
        get() = _eventShowMessage

    fun getBalance(userID: String) = viewModelScope.launch {
        _eventShowLoading.value = true
        try {
            val res = repository.getBalance(userID)

            _balance.value = res.data
        } catch (e: ApiException) {
            _eventShowMessage.value = e.message
        }

        _eventShowLoading.value = false
    }
}