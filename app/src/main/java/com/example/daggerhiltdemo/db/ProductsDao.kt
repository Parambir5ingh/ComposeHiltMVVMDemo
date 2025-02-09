package com.example.daggermvvmdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daggermvvmdemo.models.ProductModel

/*
* Created by Parambir Singh ON 2025-02-05
*/
@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(list: List<ProductModel>)

    @Query("SELECT * FROM ProductModel")
    suspend fun getProducts() : List<ProductModel>

}