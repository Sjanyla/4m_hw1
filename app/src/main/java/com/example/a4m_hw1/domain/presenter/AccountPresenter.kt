package com.example.a4m_hw1.domain.presenter

import com.example.a4m_hw1.data.model.Account

class AccountPresenter(val view: AccountContracts.View):AccountContracts.Presenter{
    override fun loadAccounts() {
        val testMockAccount = listOf(
            Account(
                id = "1",
                name = "O!bank",
                currency = "USD",
                balance = 2000
            ),
            Account(
                id = "2",
                name = "M bank",
                currency = "USD",
                balance = 1000
            ),
            Account(
                id = "3",
                name = "A bank",
                currency = "USD",
                balance = 3000
            ),
        )
        view.showAccounts(testMockAccount)
    }



}
