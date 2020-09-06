package com.nicdamun.lealapp.api

import com.nicdamun.lealapp.dtos.TransactionDTO
import com.nicdamun.lealapp.dtos.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LealApi {

    @GET("transactions")
    suspend fun getTransactions(): Response<List<TransactionDTO>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserDTO>
}