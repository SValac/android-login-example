package com.example.jetpackcomposeinstagram.login.data

import com.example.jetpackcomposeinstagram.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun  performLogin(user:String, password: String){
        api.performLogin(user, password)
    }
}