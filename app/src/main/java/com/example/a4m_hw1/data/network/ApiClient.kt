package com.example.a4m_hw1.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://68c1a5a598c818a69402a0ec.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val accountsApi: AccountsApi = retrofit.create(AccountsApi::class.java)
}