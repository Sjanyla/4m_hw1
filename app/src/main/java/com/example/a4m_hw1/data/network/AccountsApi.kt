package com.example.a4m_hw1.data.network

import android.telecom.Call
import com.example.a4m_hw1.data.model.Account
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AccountsApi {

    @GET("accounts")
    fun fetchAccounts (): retrofit2.Call<List<Account>>

    @POST("accounts")
    fun createAccount(@Body account: Account): retrofit2.Call<Account>
}