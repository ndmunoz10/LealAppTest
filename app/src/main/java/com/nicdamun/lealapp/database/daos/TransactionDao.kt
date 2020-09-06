package com.nicdamun.lealapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicdamun.lealapp.database.entities.TransactionEntity
import com.nicdamun.lealapp.database.entities.UserEntity
import com.nicdamun.lealapp.models.TransactionType

@Dao
interface TransactionDao {

    @Query("delete from `transaction`")
    suspend fun deleteAll()

    @Query("select * from `transaction` order by transactionType desc")
    suspend fun getAll(): List<TransactionEntity>

    @Query("select * from `transaction` where transactionType = :transactionType order by transactionType desc")
    suspend fun getTransactionsByType(transactionType: TransactionType): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<TransactionEntity>)

    @Query("update `transaction` set transactionType = :transactionType where id = :transactionId")
    suspend fun updateTransactionType(transactionId: Int, transactionType: TransactionType)

    @Query("update `transaction` set user = :userEntity where id = :transactionId")
    suspend fun updateTransactionUser(transactionId: Int, userEntity: UserEntity)
}