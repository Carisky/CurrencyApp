package com.example.currencyapp.data.interfaces

import com.example.currencyapp.data.models.CurrencyRates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("v6/9c23e102c4da11fe52877b1a/latest/USD")
    fun getCurrencyRates(): Call<CurrencyRates>
}
