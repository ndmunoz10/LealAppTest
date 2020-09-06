package com.nicdamun.lealapp.useCases.users

import com.nicdamun.lealapp.models.UserModel
import com.nicdamun.lealapp.repository.users.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend fun invoke(userId: Int): UserModel? {
        return userRepository.getUserById(userId)
    }
}