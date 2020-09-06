package com.nicdamun.lealapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicdamun.lealapp.models.TransactionType

@Entity(tableName = "transaction")
data class TransactionEntity (
    @PrimaryKey val id: Int? = -1,
    val userId: Int? = -1,
    val createdDate: String? = "",
    val transactionType: TransactionType,
    val commerce: CommerceEntity?,
    val branch: BranchEntity?,
    val user: UserEntity? = null
)