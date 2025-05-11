package com.example.tipapp.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.ceil

class TipViewModel : ViewModel() {
    private val _amountInput = MutableStateFlow("")
    val amountInput: StateFlow<String> = _amountInput

    private val _tipPercent = MutableStateFlow(0.20)
    val tipPercent: StateFlow<Double> = _tipPercent

    private val _roundUp = MutableStateFlow(false)
    val roundUp: StateFlow<Boolean> = _roundUp

    fun onAmountChange(newAmount: String) {
        _amountInput.value = newAmount
    }

    fun onTipChange(percent: Double) {
        _tipPercent.value = percent
    }

    fun onRoundUpChange(value: Boolean) {
        _roundUp.value = value
    }

    fun calculateTip(): Double {
        val amount = _amountInput.value.toDoubleOrNull() ?: return 0.0
        var result = amount * _tipPercent.value
        if (_roundUp.value) result = ceil(result)
        return result
    }
}
