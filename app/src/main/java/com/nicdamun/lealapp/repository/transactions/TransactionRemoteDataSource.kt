package com.nicdamun.lealapp.repository.transactions

import com.nicdamun.lealapp.api.LealApi
import com.nicdamun.lealapp.dtos.TransactionDTO
import com.nicdamun.lealapp.extensions.callServices
import com.nicdamun.lealapp.extensions.safeApiCall
import com.nicdamun.lealapp.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRemoteDataSource @Inject constructor(
    private val lealApi: LealApi
) {

    suspend fun getTransactions(): Result<List<TransactionDTO>> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    lealApi.getTransactions().callServices()
                }
            )
            when(result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Failure -> result
            }
        }
    }
}