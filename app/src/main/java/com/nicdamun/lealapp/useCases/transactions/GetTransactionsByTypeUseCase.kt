package com.nicdamun.lealapp.useCases.transactions

import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import javax.inject.Inject

class GetTransactionsByTypeUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend fun invoke(transactionType: TransactionType): List<TransactionModel> {
        return transactionRepository.getTransactionsByType(transactionType)
    }
}