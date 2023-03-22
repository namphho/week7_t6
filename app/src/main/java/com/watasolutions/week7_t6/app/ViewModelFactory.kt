package com.watasolutions.week7_t6.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.watasolutions.week7_t6.MySharedPreferences
import com.watasolutions.week7_t6.data.db.AccountDatabase
import com.watasolutions.week7_t6.screens.login.LoginViewModel
import com.watasolutions.week7_t6.screens.signup.SignUpViewModel

class ViewModelFactory(val prefs: MySharedPreferences, val accountDatabase: AccountDatabase) :
    ViewModelProvider
.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            //val prefs = app.prefs
            return LoginViewModel(prefs, accountDatabase) as T
        } else if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            //val prefs = app.prefs
            return SignUpViewModel(prefs, accountDatabase) as T
        }
        throw IllegalArgumentException("unknown view model")
    }
}