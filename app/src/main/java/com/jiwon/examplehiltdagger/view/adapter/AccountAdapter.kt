package com.jiwon.examplehiltdagger.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiwon.examplehiltdagger.data.model.Account
import com.jiwon.examplehiltdagger.databinding.AccountItemBinding
import javax.inject.Inject

class AccountAdapter @Inject constructor(): RecyclerView.Adapter<AccountAdapter.AdpaterViewHolder>(){
    private val TAG = AccountAdapter::class.java.simpleName

    var accounts : MutableList<Account> = ArrayList()

    inner class AdpaterViewHolder(
        private val binding : AccountItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(acc : Account){
            binding.apply{
                this.name.text = acc.name
                this.info.text = "num features ${acc.feature.size}"
            }
        }
    }

    internal fun addAccounts(acc : Account){
        this.accounts.add(acc)
        notifyItemInserted(accounts.size)

        Log.d(TAG, "accoutnts size : ${accounts.size}")
    }

    internal fun addAccounts(list : List<Account>){
        this.accounts.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdpaterViewHolder {
        return AdpaterViewHolder(
            AccountItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdpaterViewHolder, position: Int) {
       return holder.bind(accounts[position])
    }

    override fun getItemCount(): Int {
        return accounts.size
    }
}
