package com.example.a4m_hw1.domain.presenter

import android.adservices.adid.AdId
import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.data.model.AccountState

interface AccountContracts {
    interface View{
        fun  showAccounts(list: List<Account>)
    }
    interface Presenter{
        fun loadAccounts()
        fun addAccount(account: Account)
        fun updateFullyAccount(account: Account)
        fun updateStateAccount(accountId: String,accountState: AccountState)
        fun deleteAccount(accountId: String)

    }
}