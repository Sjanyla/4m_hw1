package com.example.a4m_hw1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a4m_hw1.databinding.ActivityMainBinding
import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.data.model.AccountState
import com.example.a4m_hw1.databinding.DialogAddAccountBinding
import com.example.a4m_hw1.ui.adapter.AccountAdapter
import com.example.a4m_hw1.ui.viewmodel.AccountViewModel

class
MainActivity : AppCompatActivity(){
    private  var _binding: ActivityMainBinding?=null
    private  val binding get()= _binding!!
    private lateinit var adapter: AccountAdapter
    private  val  viewModel: AccountViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initClicks()
        subscribeToLiveData()
    }

    private  fun subscribeToLiveData (){
        viewModel.accounts.observe(this){
            adapter.submitList(it)
        }
        viewModel.successMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.errorMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
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

                }
                .setNegativeButton("Отмена",null)
                .show()

        }


    }
    private fun showEditDialog(account: Account) {
        val binding = DialogAddAccountBinding.inflate(LayoutInflater.from(this))
        with(binding) {

            etName.setText(account.name)
            etBalance.setText(account.balance.toString())
            etCurrency.setText(account.currency)

            AlertDialog.Builder(this@MainActivity)
                .setTitle("Изменить счет")
                .setView(binding.root)
                .setPositiveButton("Изменить") { _, _ ->

                    val updatedAccount=account.copy(
                        name = etName.text.toString(),
                        currency = etCurrency.text.toString(),
                        balance = etBalance.text.toString().toInt()
                    )

                    viewModel.updateFullyAccount(updatedAccount)
                }
                .setNegativeButton("Отмена", null)
                .show()


        }
    }
    private fun initAdapter(){
        with(binding){
            adapter= AccountAdapter(
                onEdit = {
                    showEditDialog(it)
                },
                onDelete = {
                    viewModel.deleteAccount(it)
                },
                onSwitchToggle = {id, isChecked->
                    viewModel.updateStateAccount(id, AccountState(isChecked))
                }
            )
            recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
            recyclerView.adapter= adapter

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAccounts()
    }
}
