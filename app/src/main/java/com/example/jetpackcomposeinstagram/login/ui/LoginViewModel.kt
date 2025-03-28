package com.example.jetpackcomposeinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeinstagram.login.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    val loginUseCase= LoginUseCase()

    private val _email = MutableStateFlow<String>("")
    val email : StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoginEnabled = MutableStateFlow<Boolean>(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun onLoginChange(email:String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value = true
            val response = loginUseCase(email.value, password.value)
            if (response) {
                Log.i("Valac", "result ok")
            }
            _isLoading.value = false
        }
    }
}