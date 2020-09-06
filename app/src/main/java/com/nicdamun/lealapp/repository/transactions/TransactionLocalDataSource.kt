package com.nicdamun.lealapp.repository.transactions

import com.nicdamun.lealapp.database.daos.TransactionDao
import com.nicdamun.lealapp.database.entities.TransactionEntity
import com.nicdamun.lealapp.database.entities.UserEntity
import com.nicdamun.lealapp.models.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionLocalDataSource @Inject constructor(
    private val transactionDao: TransactionDao
) {

    suspend fun deleteAll(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                transactionDao.deleteAll()
                true
            } catch (exception: Exception) {
                exception.printStackTrace()
                false
            }
        }
    }

    suspend fun getTransactionsByType(transactionType: TransactionType): List<TransactionEntity> {
        return withContext(Dispatchers.IO) {
            transactionDao.getTransactionsByType(transactionType)
        }
    }

    suspend fun getTransactions(): List<TransactionEntity> {
        return withContext(Dispatchers.IO) {
            transactionDao.getAll()
        }
    }

    suspend fun saveTransactions(transactions: List<TransactionEntity>): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                transactionDao.insertAll(transactions)
                true
            } catch (exception: Exception) {
                exception.printStackTrace()
                false
            }
        }
    }

    suspend fun updateTransactionType(transactionId: Int, transactionType: TransactionType) {
        return withContext(Dispatchers.IO) {
            try {
                transactionDao.updateTransactionType(transactionId, transactionType)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    suspend fun updateTransactionUser(transactionId: Int, userEntity: UserEntity): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                transactionDao.updateTransactionUser(transactionId, userEntity)
                true
            } catch (exception: Exception) {
                exception.printStackTrace()
                false
            }
        }
    }
}