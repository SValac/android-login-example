package com.example.jetpackcomposeinstagram.login.ui

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel:ViewModel() {
    private val _email = MutableStateFlow<String>("")
    val email : StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoginEnabled = MutableStateFlow<Boolean>(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled.asStateFlow()

    fun onLoginChange(email:String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }
}