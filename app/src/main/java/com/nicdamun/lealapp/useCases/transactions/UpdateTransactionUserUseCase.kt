package com.nicdamun.lealapp.useCases.transactions

import com.nicdamun.lealapp.models.UserModel
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUserUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend fun invoke(transactionId: Int, userModel: UserModel?): Boolean {
        return if (userModel != null) transactionRepository.updateTransactionUser(transactionId, userModel)
        else false
    }
}