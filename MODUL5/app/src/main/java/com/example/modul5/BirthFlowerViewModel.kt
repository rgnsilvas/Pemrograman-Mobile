package com.example.modul5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.model.FlowerRemote
import com.example.modul5.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class BirthFlowerViewModel : ViewModel() {

    private val _flowers = MutableStateFlow<List<FlowerRemote>>(emptyList())
    val flowers: StateFlow<List<FlowerRemote>> = _flowers

    init {
        fetchFlowers()
    }

    private fun fetchFlowers() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getFlowers()
                _flowers.value = response
                Log.d("BirthFlowerViewModel", "Loaded ${response.size} flowers")
            } catch (e: Exception) {
                Log.e("BirthFlowerViewModel", "API Error", e)
            }
        }
    }
}
