package com.nicdamun.lealapp.repository.users

import com.nicdamun.lealapp.helpers.Result
import com.nicdamun.lealapp.mappers.toUserModel
import com.nicdamun.lealapp.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
){

    suspend fun getUserById(userId: Int): UserModel? {
        return when(val result = userRemoteDataSource.getUserById(userId)) {
            is Result.Success -> result.data.toUserModel()
            is Result.Failure -> null
        }
    }

}