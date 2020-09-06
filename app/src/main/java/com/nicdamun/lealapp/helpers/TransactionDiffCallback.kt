package com.nicdamun.lealapp.helpers

import androidx.recyclerview.widget.DiffUtil
import com.nicdamun.lealapp.models.TransactionModel

class TransactionDiffCallback: DiffUtil.ItemCallback<TransactionModel>() {

    override fun areItemsTheSame(oldItem: TransactionModel, newItem: TransactionModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransactionModel, newItem: TransactionModel): Boolean {
        return oldItem == newItem
    }
}