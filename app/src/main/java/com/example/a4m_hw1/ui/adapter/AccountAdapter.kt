package com.example.a4m_hw1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a4m_hw1.R
import com.example.a4m_hw1.data.model.Account
import com.example.a4m_hw1.databinding.ActivityMainBinding
import com.example.a4m_hw1.databinding.ItemAccountBinding



class AccountAdapter(
    val onEdit:(Account)-> Unit,
    val onDelete:(String)-> Unit,
    val onSwitchToggle:(String, Boolean)-> Unit
) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    private val items = mutableListOf<Account>()
    fun submitList(data: List<Account>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountAdapter.AccountViewHolder {
        val binding= ItemAccountBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AccountViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: AccountAdapter.AccountViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class AccountViewHolder(private val binding:ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            tvName.text =account.name
            tvBalance.text= "${account.balance} ${account.currency}"
            btnEdit.setOnClickListener{
                onEdit(account)
            }
            btnDelete.setOnClickListener{
                account.id?.let { onDelete(it) }

            }

         switcher.setOnCheckedChangeListener{ buttonView,isChecked->
             account.id?.let {
                 onSwitchToggle (it,isChecked)
             }
         }
        }
    }


}


