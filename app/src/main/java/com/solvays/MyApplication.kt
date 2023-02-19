package com.solvays

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.solvays.util.PrefManager

class MyApplication : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var prefManager: PrefManager
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        prefManager = PrefManager(applicationContext, Gson())
    }

}