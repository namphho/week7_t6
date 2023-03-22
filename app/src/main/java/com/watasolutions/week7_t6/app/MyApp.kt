package com.watasolutions.week7_t6.app

import android.app.Application
import com.watasolutions.week7_t6.MySharedPreferences
import com.watasolutions.week7_t6.data.db.AccountDatabase

class MyApp : Application() {
    lateinit var prefs : MySharedPreferences
    lateinit var db : AccountDatabase

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences()
        prefs.init(this)
        db = AccountDatabase.getDatabase(this)
    }
}