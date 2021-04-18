package com.mobikasa.recyclerviewsample.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobikasa.recyclerviewsample.ui.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(var mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        } else {
            throw IllegalArgumentException("No Class Found")
        }
    }
}