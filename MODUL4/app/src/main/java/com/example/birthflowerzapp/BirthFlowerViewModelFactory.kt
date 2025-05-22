package com.example.birthflowerzapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BirthFlowerViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BirthFlowerViewModel::class.java)) {
            return BirthFlowerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
