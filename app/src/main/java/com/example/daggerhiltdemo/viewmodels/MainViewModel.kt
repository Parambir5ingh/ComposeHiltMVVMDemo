package com.example.daggermvvmdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvmdemo.models.ProductModel
import com.example.daggermvvmdemo.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* Created by Parambir Singh ON 2025-02-04
*/
@HiltViewModel
class MainViewModel @Inject constructor(val repository: ProductRepository) : ViewModel() {

    var _progress = MutableLiveData<Boolean>()
    var progress: LiveData<Boolean> = _progress

    var selectedProduct: ProductModel? = null

    val productsLiveData: StateFlow<List<ProductModel>>?
        get() = repository.products

    init {
        viewModelScope.launch {
            _progress.postValue(true)
            repository.getProducts()
            _progress.postValue(false)
            Log.w("VIEWMODEL CALLED", "-----------------------------")
        }
    }

}