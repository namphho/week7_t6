package com.watasolutions.week7_t6.screens.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watasolutions.week7_t6.MySharedPreferences
import com.watasolutions.week7_t6.data.db.Account
import com.watasolutions.week7_t6.data.db.AccountDatabase
import kotlinx.coroutines.launch

class SignUpViewModel(val prefs: MySharedPreferences, val db: AccountDatabase) :
    ViewModel() {
    private var _registerSuccessEvent: MutableLiveData<Boolean> =
        MutableLiveData()
    val registerSuccessEvent: LiveData<Boolean>
        get() = _registerSuccessEvent


    fun registerUser(username: String, pass: String) {
        prefs.saveUsername(username);
        prefs.savePassword(pass)
        _registerSuccessEvent.value = true;
    }

    fun registerUserWithDB(username: String, pass: String) {
        viewModelScope.launch {
            db.accountDao()
                .insert(Account(username = username, password = pass))
            _registerSuccessEvent.value = true;
        }
    }

}