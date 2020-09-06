package com.nicdamun.lealapp.useCases.transactions

import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import javax.inject.Inject

class GetLocalTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend fun invoke(): List<TransactionModel> {
        return transactionRepository.getLocalTransactions()
    }
}