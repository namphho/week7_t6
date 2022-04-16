package com.watasolutions.week7_t6.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.watasolutions.week7_t6.MySharedPreferences
import com.watasolutions.week7_t6.models.User

class LoginViewModel(val prefs: MySharedPreferences) : ViewModel() {
    private var _saveSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val saveSuccessEvent: LiveData<Boolean>
        get() = _saveSuccessEvent

    private var _loadSuccessEvent: MutableLiveData<User> = MutableLiveData()
    val loadSuccessEvent: LiveData<User>
        get() = _loadSuccessEvent

    fun saveUser(user: String, password: String) {
        prefs.saveUsername(user)
        prefs.savePassword(password)
        _saveSuccessEvent.value = true
    }

    fun loadUser(){
        val username = prefs.getUsername()
        val pass = prefs.getPassword()
        _loadSuccessEvent.postValue(User(username ?: "", pass ?: ""))
    }
}