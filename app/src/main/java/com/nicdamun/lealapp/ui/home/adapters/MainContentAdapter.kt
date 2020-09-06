package com.nicdamun.lealapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.lealapp.databinding.MainContentTransactionItemBinding
import com.nicdamun.lealapp.extensions.loadImage
import com.nicdamun.lealapp.extensions.tryCast
import com.nicdamun.lealapp.helpers.TransactionDiffCallback
import com.nicdamun.lealapp.models.TransactionModel

class MainContentAdapter(private val onItemDeleted: (position: Int, transactionModel: TransactionModel) -> Unit):
    ListAdapter<TransactionModel, RecyclerView.ViewHolder>(TransactionDiffCallback()) {


    fun deleteItem(position: Int) {
        val recentlyDeletedItem = getItem(position)
        val currentList = currentList.toMutableList()
        currentList.removeAt(position)
        submitList(currentList)
        onItemDeleted(position, recentlyDeletedItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.tryCast<TransactionViewHolder>()?.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TransactionViewHolder(
            MainContentTransactionItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class TransactionViewHolder(private val mainContentTransactionItemBinding: MainContentTransactionItemBinding):
        RecyclerView.ViewHolder(mainContentTransactionItemBinding.root) {

        fun bind(transactionModel: TransactionModel) {
            val user = transactionModel.user
            val commerce = transactionModel.commerce
            mainContentTransactionItemBinding.run {
                ivUserTransactionAvatar.loadImage(user?.photo)
                tvUserNameValue.text = user?.name
                tvUserCommerceValue.text = commerce?.name.orEmpty()
                tvUserPointsValue.text = commerce?.valueToPoints.toString()
            }
        }
    }
}