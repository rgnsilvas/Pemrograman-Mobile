package com.example.modul5

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.datastore.ThemePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> get() = _isDarkMode

    init {
        viewModelScope.launch {
            val current = ThemePreference.getThemeSetting(getApplication()).first()
            _isDarkMode.value = current
        }
    }

    fun toggleTheme() {
        val context = getApplication<Application>()
        viewModelScope.launch {
            val newValue = !_isDarkMode.value
            ThemePreference.saveThemeSetting(context, newValue)
            _isDarkMode.value = newValue
        }
    }
}
