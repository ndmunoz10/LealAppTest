package com.nicdamun.lealapp.useCases.transactions

import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import javax.inject.Inject

class UpdateTransactionTypeUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
){

    suspend fun invoke(transactionId: Int, transactionType: TransactionType) {
        transactionRepository.updateTransactionType(transactionId, transactionType)
    }
}