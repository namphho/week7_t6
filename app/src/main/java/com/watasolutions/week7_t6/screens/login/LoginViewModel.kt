package com.watasolutions.week7_t6.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.watasolutions.week7_t6.MySharedPreferences
import com.watasolutions.week7_t6.app.Event

class LoginViewModel(val prefs: MySharedPreferences) : ViewModel() {
    private var _loginSuccessEvent: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()
    val saveSuccessEvent: LiveData<Event<Boolean>>
        get() = _loginSuccessEvent

    private var _loginFailedEvent: MutableLiveData<String> = MutableLiveData()
    val loginFailedEvent: LiveData<String>
        get() = _loginFailedEvent


    fun login(user: String, password: String) {
        val storedUsername = prefs.getUsername()
        val storePassword = prefs.getPassword()
        if (storedUsername != user || storedUsername.isEmpty()) {
            _loginSuccessEvent.value = Event(false)
            _loginFailedEvent.value = "Tên đăng nhập không hợp lệ"
            return;
        }
        if (storePassword != password || storedUsername.isEmpty()) {
            _loginSuccessEvent.value = Event(false)
            _loginFailedEvent.value = "Mật khẩu không hợp lệ"
            return;
        }
        Log.e("success", "login success")
        _loginSuccessEvent.value = Event(true)
    }

}