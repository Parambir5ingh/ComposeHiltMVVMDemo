package com.example.daggermvvmdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.daggermvvmdemo.models.ProductModel

/*
* Created by Parambir Singh ON 2025-02-05
*/

@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao
}