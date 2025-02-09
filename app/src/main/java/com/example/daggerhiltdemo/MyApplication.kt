package com.example.daggerhiltdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* Created by Parambir Singh ON 2025-02-08
*/
@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }

}