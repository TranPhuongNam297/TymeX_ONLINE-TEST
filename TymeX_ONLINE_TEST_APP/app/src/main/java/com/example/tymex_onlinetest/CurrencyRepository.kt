package com.example.tymex_onlinetest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class CurrencyRepository {
    private val api = Retrofit.Builder()
        .baseUrl("https://open.er-api.com/v6/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    suspend fun convertCurrency(amount: Double, from: String, to: String): Double {
        val response = api.getExchangeRate(from)
        val rate = response.rates[to] ?: throw Exception("Currency not found")
        return amount * rate
    }

    suspend fun getCurrencyList(): List<String> {
        val response = api.getExchangeRate("USD")
        return response.rates.keys.toList()
    }
}

interface CurrencyApi {
    @GET("latest")
    suspend fun getExchangeRate(@Query("base") base: String): ExchangeRateResponse
}

data class ExchangeRateResponse(
    val base: String,
    val rates: Map<String, Double>
)

