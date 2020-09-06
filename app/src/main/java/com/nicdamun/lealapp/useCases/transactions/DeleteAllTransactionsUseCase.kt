package com.nicdamun.lealapp.useCases.transactions

import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import javax.inject.Inject

class DeleteAllTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend fun invoke(): Boolean {
        return transactionRepository.deleteAllTransactions()
    }
}