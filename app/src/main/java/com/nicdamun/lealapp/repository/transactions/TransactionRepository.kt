package com.nicdamun.lealapp.repository.transactions

import com.nicdamun.lealapp.helpers.Result
import com.nicdamun.lealapp.mappers.toTransactionEntity
import com.nicdamun.lealapp.mappers.toTransactionModels
import com.nicdamun.lealapp.mappers.toUserEntity
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.models.UserModel
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val transactionRemoteDataSource: TransactionRemoteDataSource
) {

    suspend fun deleteAllTransactions(): Boolean {
        return transactionLocalDataSource.deleteAll()
    }

    suspend fun fetchTransactions(): List<TransactionModel> {
        val localTransactions = getLocalTransactions()
        return if (localTransactions.isNullOrEmpty()) {
            when(val result = transactionRemoteDataSource.getTransactions()) {
                is Result.Success -> {
                    val transactionDTOs = result.data
                    val transactionEntities = transactionDTOs.map { transactionDTO -> transactionDTO.toTransactionEntity() }
                    val transactionsWereSaved = transactionLocalDataSource.saveTransactions(transactionEntities)
                    if (transactionsWereSaved) getLocalTransactions()
                    else emptyList()
                }
                is Result.Failure -> emptyList()
            }
        } else {
            localTransactions
        }
    }

    suspend fun getLocalTransactions(): List<TransactionModel> {
        return transactionLocalDataSource.getTransactions().toTransactionModels()
    }

    suspend fun getTransactionsByType(transactionType: TransactionType): List<TransactionModel> {
        return transactionLocalDataSource.getTransactionsByType(transactionType).toTransactionModels()
    }

    suspend fun updateTransactionType(transactionId: Int, transactionType: TransactionType) {
        transactionLocalDataSource.updateTransactionType(transactionId, transactionType)
    }

    suspend fun updateTransactionUser(transactionId: Int, userModel: UserModel): Boolean {
        val userEntity = userModel.toUserEntity()
        return transactionLocalDataSource.updateTransactionUser(transactionId, userEntity)
    }
}