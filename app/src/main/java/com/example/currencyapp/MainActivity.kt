package com.example.currencyapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyapp.data.api.ApiClient
import com.example.currencyapp.data.models.CurrencyRates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var amountInput: EditText
    private lateinit var currencySpinner: Spinner
    private lateinit var resultText: TextView
    private lateinit var updateRatesButton: Button

    private var rates: Map<String, Double> = mapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountInput = findViewById(R.id.amountInput)
        currencySpinner = findViewById(R.id.currencySpinner)
        resultText = findViewById(R.id.resultText)
        updateRatesButton = findViewById(R.id.updateRatesButton)

        // Ініціалізація Spinner з валютами
        val currencies = listOf("USD", "EUR", "UAH")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = adapter

        updateRatesButton.setOnClickListener {
            updateCurrencyRates()
        }
    }

    private fun updateCurrencyRates() {
        ApiClient.currencyApi.getCurrencyRates().enqueue(object : Callback<CurrencyRates> {
            override fun onResponse(call: Call<CurrencyRates>, response: Response<CurrencyRates>) {
                if (response.isSuccessful) {
                    rates = response.body()?.conversion_rates ?: mapOf()
                    Toast.makeText(this@MainActivity, "Rates updated", Toast.LENGTH_SHORT).show()
                    convertCurrency()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to update rates", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CurrencyRates>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun convertCurrency() {
        val amount = amountInput.text.toString().toDoubleOrNull() ?: return
        val selectedCurrency = currencySpinner.selectedItem.toString()

        val rate = rates[selectedCurrency] ?: 1.0
        val result = amount * rate

        resultText.text = String.format("%.2f", result)
    }
}

