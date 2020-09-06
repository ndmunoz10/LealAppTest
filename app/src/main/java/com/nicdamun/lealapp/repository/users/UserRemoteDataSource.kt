package com.nicdamun.lealapp.repository.users

import com.nicdamun.lealapp.api.LealApi
import com.nicdamun.lealapp.dtos.UserDTO
import com.nicdamun.lealapp.extensions.callServices
import com.nicdamun.lealapp.extensions.safeApiCall
import com.nicdamun.lealapp.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val lealApi: LealApi
) {

    suspend fun getUserById(userId: Int): Result<UserDTO> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    lealApi.getUserById(userId).callServices()
                }
            )
            when(result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Failure -> result
            }
        }
    }
}