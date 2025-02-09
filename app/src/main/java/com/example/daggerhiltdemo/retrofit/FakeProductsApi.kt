package com.example.daggermvvmdemo.retrofit

import com.example.daggermvvmdemo.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET

/*
* Created by Parambir Singh ON 2025-02-04
*/interface FakeProductsApi {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductModel>>
}