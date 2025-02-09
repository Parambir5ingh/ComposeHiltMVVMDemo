package com.example.daggermvvmdemo.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggermvvmdemo.db.AppDatabase
import com.example.daggermvvmdemo.models.ProductModel
import com.example.daggermvvmdemo.retrofit.FakeProductsApi
import javax.inject.Inject

/*
* Created by Parambir Singh ON 2025-02-04
*/
class ProductRepository @Inject constructor(val fakeProductsApi: FakeProductsApi, val appDb: AppDatabase) {

    private val _products = MutableLiveData<List<ProductModel>>()
    var products: LiveData<List<ProductModel>>? = null
        get() = _products

    suspend fun getProducts() {
        val result = fakeProductsApi.getProducts()
        if (result.isSuccessful && result.body() != null) {
            appDb.getProductsDao().addProducts(result.body()!!)
            _products.postValue(result.body())
        }
    }
}