package com.example.currencyapp.data.api

import com.example.currencyapp.data.interfaces.CurrencyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://v6.exchangerate-api.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyApi: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}

