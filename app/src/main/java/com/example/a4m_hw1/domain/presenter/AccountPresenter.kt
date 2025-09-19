package com.example.a4m_hw1.domain.presenter

import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.data.model.AccountState
import com.example.a4m_hw1.data.network.ApiClient
import okhttp3.Call
import okhttp3.Response
import retrofit2.Callback

class AccountPresenter(val view: AccountContracts.View) : AccountContracts.Presenter {
    override fun loadAccounts() {
        ApiClient.accountsApi.fetchAccounts().enqueue(object : Callback<List<Account>> {
            override fun onResponse(
                call: retrofit2.Call<List<Account>>,
                response: retrofit2.Response<List<Account>>) {
                if (response.isSuccessful) view.showAccounts(response.body() ?: emptyList())
            }

            override fun onFailure(call: retrofit2.Call<List<Account>>, t: Throwable) {

            }
        })

    }

    override fun addAccount(account: Account) {
        ApiClient.accountsApi.createAccount(account).enqueue(object : Callback<Account> {
            override fun onResponse(call: retrofit2.Call<Account>, response: retrofit2.Response<Account>) {
                if (response.isSuccessful) loadAccounts()
            }

            override fun onFailure(call: retrofit2.Call<Account>, t: Throwable) {

            }

        })
    }

    override fun updateFullyAccount(account: Account) {
        ApiClient.accountsApi.updateFullyAccount(account.id!!,account).enqueue(object : Callback<Unit>{
            override fun onResponse(call: retrofit2.Call<Unit?>, response: retrofit2.Response<Unit>) {
                if (response.isSuccessful) loadAccounts()
            }

            override fun onFailure(call: retrofit2.Call<Unit>, t: Throwable) {

            }

        })
        }

    override fun updateStateAccount(accountId: String,accountState: AccountState){
        ApiClient.accountsApi.updateAccountState(accountId,accountState).enqueue(object : Callback<Unit>{
            override fun onResponse(
                call: retrofit2.Call<Unit?>,
                response: retrofit2.Response<Unit?>
            ) {
                if (response.isSuccessful) loadAccounts()
            }

            override fun onFailure(call: retrofit2.Call<Unit>, t: Throwable) {
        }
    })
    }


    override fun deleteAccount(accountId: String) {
        ApiClient.accountsApi.deleteAccount(accountId).enqueue(object : Callback<Unit>{

            override fun onResponse(
                call: retrofit2.Call<Unit?>,
                response: retrofit2.Response<Unit?>
            ) {
                if (response.isSuccessful) loadAccounts()
            }

            override fun onFailure(call: retrofit2.Call<Unit>, t: Throwable) {
            }
        })
    }
}