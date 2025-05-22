package com.example.birthflowerzapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.util.Log

class BirthFlowerViewModel : ViewModel() {

    private val _flowers = MutableStateFlow(flowersList)
    val flowers: StateFlow<List<Flowers>> get() = _flowers

    private val _selectedFlowerIndex = MutableStateFlow<Int?>(null)
    val selectedFlowerIndex: StateFlow<Int?> get() = _selectedFlowerIndex

    fun selectFlower(index: Int) {
        _selectedFlowerIndex.value = index
        Log.d("BirthFlowerViewModel", "Tombol Detail ditekan untuk bunga: ${flowersList[index].name}")
    }

    init {
        Log.d("BirthFlowerViewModel", "Data bunga ditambahkan: ${flowersList.size} item")
    }
}
