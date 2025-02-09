package com.example.daggermvvmdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvmdemo.models.ProductModel
import com.example.daggermvvmdemo.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* Created by Parambir Singh ON 2025-02-04
*/
@HiltViewModel
class MainViewModel @Inject constructor(val repository: ProductRepository) : ViewModel() {

    val productsLiveData: LiveData<List<ProductModel>>?
        get() = repository.products

    init {
        viewModelScope.launch {
            repository.getProducts()
        }
    }

}