package com.example.a4m_hw1.domain.presenter

import com.example.a4m_hw1.data.model.Account

interface AccountContracts {
    interface View{
        fun  showAccounts(list: List<Account>)
    }
    interface Presenter{
        fun loadAccounts()

    }
}