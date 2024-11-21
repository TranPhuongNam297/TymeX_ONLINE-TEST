package com.example.tymex_onlinetest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class CurrencyViewModel : ViewModel() {
    private val repository = CurrencyRepository()

    private val _conversionState = MutableStateFlow<ConversionState>(ConversionState.Initial)
    val conversionState: StateFlow<ConversionState> = _conversionState

    private val _currencyList = MutableStateFlow<List<String>>(emptyList())
    val currencyList: StateFlow<List<String>> = _currencyList

    init {
        loadCurrencyList()
    }

    private fun loadCurrencyList() {
        viewModelScope.launch {
            try {
                _currencyList.value = repository.getCurrencyList()
            } catch (e: Exception) {
                _currencyList.value = listOf("USD", "EUR", "GBP", "JPY", "AUD", "VND") //Defaul list when error
            }
        }
    }

    fun convertCurrency(amount: Double, from: String, to: String) {
        viewModelScope.launch {
            try {
                _conversionState.value = ConversionState.Loading
                val convertedAmount = repository.convertCurrency(amount, from, to)
                _conversionState.value = ConversionState.Success(
                    amount = amount,
                    fromCurrency = from,
                    convertedAmount = convertedAmount,
                    toCurrency = to
                )
            } catch (e: Exception) {
                _conversionState.value = ConversionState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
