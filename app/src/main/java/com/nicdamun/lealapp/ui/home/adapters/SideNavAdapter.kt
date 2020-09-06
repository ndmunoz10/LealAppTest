package com.nicdamun.lealapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.lealapp.databinding.OpenedSideNavItemBinding
import com.nicdamun.lealapp.databinding.UnopenedSideNavItemBinding
import com.nicdamun.lealapp.extensions.tryCast
import com.nicdamun.lealapp.helpers.TransactionDiffCallback
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.ui.home.TransactionInteraction

class SideNavAdapter (private val transactionInteraction: TransactionInteraction):
    ListAdapter<TransactionModel, RecyclerView.ViewHolder>(TransactionDiffCallback()) {

    companion object {
        const val OPENED = 0
        const val UNOPENED = 1
    }

    override fun getItemViewType(position: Int): Int {
        val transactionModel = getItem(position)
        return when(transactionModel.transactionType) {
            TransactionType.Opened -> OPENED
            TransactionType.Unopened -> UNOPENED
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transactionModel = getItem(position)
        when(holder.itemViewType) {
            OPENED -> holder.tryCast<OpenedTransactionViewHolder>()?.bind(transactionModel)
            else -> holder.tryCast<UnopenedTransactionViewHolder>()?.bind(transactionModel)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return when(viewType) {
            OPENED -> OpenedTransactionViewHolder(
                OpenedSideNavItemBinding.inflate(inflater, viewGroup, false)
            )
            else -> UnopenedTransactionViewHolder(
                UnopenedSideNavItemBinding.inflate(inflater, viewGroup, false)
            )
        }
    }

    class OpenedTransactionViewHolder(private val itemSideNavBinding: OpenedSideNavItemBinding) : RecyclerView.ViewHolder(itemSideNavBinding.root) {
        fun bind(transactionModel: TransactionModel) {
            itemSideNavBinding.run {
                tvNavText.text = transactionModel.commerce?.name.orEmpty()
            }
        }
    }

    inner class UnopenedTransactionViewHolder(private val itemSideNavBinding: UnopenedSideNavItemBinding) : RecyclerView.ViewHolder(itemSideNavBinding.root) {
        fun bind(transactionModel: TransactionModel) {
            itemView.setOnClickListener {
                transactionInteraction.onTransactionClicked(transactionModel)
            }
            itemSideNavBinding.run {
                tvNavText.text = transactionModel.commerce?.name.orEmpty()
            }
        }
    }
}