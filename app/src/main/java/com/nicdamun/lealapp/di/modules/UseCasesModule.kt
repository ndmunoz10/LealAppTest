package com.nicdamun.lealapp.di.modules

import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import com.nicdamun.lealapp.repository.users.UserRepository
import com.nicdamun.lealapp.useCases.transactions.GetTransactionsByTypeUseCase
import com.nicdamun.lealapp.useCases.transactions.LoadTransactionsUseCase
import com.nicdamun.lealapp.useCases.transactions.UpdateTransactionTypeUseCase
import com.nicdamun.lealapp.useCases.users.GetUserByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetTransactionsByType(
        transactionRepository: TransactionRepository
    ): GetTransactionsByTypeUseCase {
        return GetTransactionsByTypeUseCase(transactionRepository)
    }

    @Provides
    fun provideGetUserByIdUseCase(
        userRepository: UserRepository
    ): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository)
    }

    @Provides
    fun provideLoadTransactionsUseCase(
        transactionRepository: TransactionRepository
    ): LoadTransactionsUseCase {
        return LoadTransactionsUseCase(transactionRepository)
    }

    @Provides
    fun provideUpdateTransactionTypeUseCase(
        transactionRepository: TransactionRepository
    ): UpdateTransactionTypeUseCase {
        return UpdateTransactionTypeUseCase(transactionRepository)
    }
}