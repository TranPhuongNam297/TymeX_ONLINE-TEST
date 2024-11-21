package com.example.tymex_onlinetest

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var amountEditText: EditText
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var swapButton: ImageButton
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]

        initViews()
        initSpinners()
        setupListeners()
        observeViewModel()
    }

    private fun initViews() {
        amountEditText = findViewById(R.id.amountEditText)
        fromSpinner = findViewById(R.id.fromCurrencySpinner)
        toSpinner = findViewById(R.id.toCurrencySpinner)
        swapButton = findViewById(R.id.swapButton)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initSpinners() {
        lifecycleScope.launch {
            viewModel.currencyList.collect { currencies ->
                if (currencies.isNotEmpty()) {
                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item,
                        currencies
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    fromSpinner.adapter = adapter
                    toSpinner.adapter = adapter

                    fromSpinner.setSelection(0)
                    toSpinner.setSelection(1)
                }
            }
        }
    }

    private fun setupListeners() {
        convertButton.setOnClickListener {
            val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
            val fromCurrency = fromSpinner.selectedItem.toString()
            val toCurrency = toSpinner.selectedItem.toString()

            viewModel.convertCurrency(amount, fromCurrency, toCurrency)
        }

        swapButton.setOnClickListener {
            val fromCurrency = fromSpinner.selectedItemPosition
            val toCurrency = toSpinner.selectedItemPosition

            fromSpinner.setSelection(toCurrency)
            toSpinner.setSelection(fromCurrency)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.conversionState.collect { state ->
                when (state) {
                    is ConversionState.Success -> {
                        progressBar.visibility = View.GONE
                        resultTextView.visibility = View.VISIBLE

                        // Định dạng kết quả với DecimalFormat
                        val formattedResult = formatAmount(state.convertedAmount)
                        val formattedAmount = formatAmount(state.amount)

                        resultTextView.text = "$formattedAmount ${state.fromCurrency} = $formattedResult ${state.toCurrency}"
                        resultTextView.setTextColor(getColor(android.R.color.black))
                    }
                    is ConversionState.Error -> {
                        progressBar.visibility = View.GONE
                        resultTextView.visibility = View.VISIBLE
                        resultTextView.text = state.message
                        resultTextView.setTextColor(getColor(android.R.color.holo_red_dark))
                    }
                    is ConversionState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        resultTextView.visibility = View.GONE
                    }
                    else -> {
                        progressBar.visibility = View.GONE
                        resultTextView.visibility = View.GONE
                    }
                }
            }
        }
    }

    // Hàm định dạng số với dấu phân cách và ba chữ số sau dấu thập phân
    private fun formatAmount(amount: Double): String {
        val decimalFormat = DecimalFormat("#,###.000")
        return decimalFormat.format(amount)
    }
}
