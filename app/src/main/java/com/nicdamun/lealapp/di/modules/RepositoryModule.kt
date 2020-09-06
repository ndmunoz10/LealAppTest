package com.nicdamun.lealapp.di.modules

import com.nicdamun.lealapp.repository.transactions.TransactionLocalDataSource
import com.nicdamun.lealapp.repository.transactions.TransactionRemoteDataSource
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import com.nicdamun.lealapp.repository.users.UserRemoteDataSource
import com.nicdamun.lealapp.repository.users.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    fun provideTransactionRepository(
        transactionLocalDataSource: TransactionLocalDataSource,
        transactionRemoteDataSource: TransactionRemoteDataSource
    ): TransactionRepository {
        return TransactionRepository(transactionLocalDataSource, transactionRemoteDataSource)
    }

    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepository(userRemoteDataSource)
    }

}