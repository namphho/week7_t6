package com.watasolutions.week7_t6.screens.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.watasolutions.week7_t6.MySharedPreferences

class SignUpViewModel(val prefs: MySharedPreferences) : ViewModel() {
    private var _registerSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccessEvent: LiveData<Boolean>
        get() = _registerSuccessEvent


    fun registerUser(username: String, pass: String) {
        prefs.saveUsername(username);
        prefs.savePassword(pass)
        _registerSuccessEvent.value = true;
    }

}