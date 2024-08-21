package com.example.currencyapp.data.models

data class CurrencyRates(
    val base_code: String,
    val conversion_rates: Map<String, Double>
)