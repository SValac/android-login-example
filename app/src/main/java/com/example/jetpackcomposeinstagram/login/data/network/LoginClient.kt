package com.example.jetpackcomposeinstagram.login.data.network

import com.example.jetpackcomposeinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/e64bed32-852b-4e49-8921-f952a2c3ebcc")
    suspend fun performLogin(): Response<LoginResponse>
}