package com.example.a4m_hw1.data.network

import android.telecom.Call
import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.data.model.AccountState
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface AccountsApi {

    @GET("accounts")
    fun fetchAccounts (): retrofit2.Call<List<Account>>

    @POST("accounts")
    fun createAccount(@Body account: Account): retrofit2.Call<Account>
    @PUT("accounts/{id}")
    fun updateFullyAccount(
        @Path("id") accountId: String,
        @Body account: Account
    ): retrofit2.Call<Unit>

    @PATCH("accounts/{id}")
    fun updateAccountState(
        @Path("id") accountId: String,
        @Body accountState: AccountState
    ): retrofit2.Call<Unit>

    @DELETE ("accounts/{id}")
    fun deleteAccount(
        @Path("id")accountId: String
    ): retrofit2.Call<Unit>
}