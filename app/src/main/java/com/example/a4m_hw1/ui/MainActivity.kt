package com.example.a4m_hw1.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a4m_hw1.databinding.ActivityMainBinding
import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.databinding.DialogAddAccountBinding
import com.example.a4m_hw1.domain.presenter.AccountContracts
import com.example.a4m_hw1.domain.presenter.AccountPresenter
import com.example.a4m_hw1.ui.adapter.AccountAdapter

class MainActivity : AppCompatActivity(), AccountContracts.View {
    private  var _binding: ActivityMainBinding?=null
    private  val binding get()= _binding!!
    private lateinit var presenter: AccountContracts.Presenter
    private lateinit var adapter: AccountAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter= AccountPresenter(this)
        initAdapter()
        initClicks()
    }
    private fun initClicks () {
        with(binding){
            btnAdd.setOnClickListener {
                showAddDialog()
            }
        }
    }
    private  fun showAddDialog(){
        val binding= DialogAddAccountBinding.inflate(LayoutInflater.from(this))
        with(binding){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Добавить счет")
                .setView(binding.root)
                .setPositiveButton("Добавить") {_,_ ->
                    val account=Account(
                        name=etName.text.toString(),
                        currency = etCurrency.text.toString(),
                        balance = etBalance.text.toString().toInt()
                    )
                    presenter.addAccount(account)
                }
                .setNegativeButton("Отмена",null)
                .show()
        }

    }
    private fun initAdapter(){
        with(binding){
            adapter= AccountAdapter()
            recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
            recyclerView.adapter= adapter

        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAccounts()
    }

    override fun showAccounts(list: List<Account>) {
        adapter.submitList(list)
    }
}
