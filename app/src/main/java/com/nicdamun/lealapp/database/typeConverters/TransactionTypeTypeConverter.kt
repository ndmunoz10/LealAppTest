package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.nicdamun.lealapp.models.TransactionType

class TransactionTypeTypeConverter {

    companion object {
        const val OPENED = 0
        const val UNOPENED = 1
    }

    @TypeConverter
    fun transactionTypeToInt(transactionType: TransactionType): Int {
        return when(transactionType) {
            TransactionType.Opened -> OPENED
            TransactionType.Unopened -> UNOPENED
        }
    }

    @TypeConverter
    fun intToTransactionType(intTransactionType: Int): TransactionType {
        return when(intTransactionType) {
            UNOPENED -> TransactionType.Unopened
            else -> TransactionType.Opened
        }
    }
}