package com.nicdamun.lealapp.mappers

import com.nicdamun.lealapp.database.entities.TransactionEntity
import com.nicdamun.lealapp.dtos.TransactionDTO
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType

fun TransactionDTO.toTransactionModel(): TransactionModel? {
    return if (id == null || userId == null) {
        null
    } else {
        return TransactionModel(
            id = id,
            userId = userId,
            transactionType = TransactionType.Unopened,
            createdDate = createdDate.orEmpty(),
            commerce = commerce?.toCommerceModel(),
            branchModel = branch?.toBranchModel()
        )
    }
}

fun TransactionDTO.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        userId = userId,
        createdDate = createdDate,
        transactionType = TransactionType.Unopened,
        commerce = commerce?.toCommerceEntity(),
        branch = branch?.toBranchEntity()
    )
}

fun TransactionEntity.toTransactionModel(): TransactionModel {
    return TransactionModel(
        id = id ?: 0,
        userId = userId ?: 0,
        transactionType = transactionType,
        createdDate = createdDate.orEmpty(),
        commerce = commerce?.toCommerceModel(),
        branchModel = branch?.toBranchModel(),
        user = user?.toUserModel()
    )
}

fun List<TransactionEntity>.toTransactionModels(): List<TransactionModel> {
    return this.map { transactionEntity -> transactionEntity.toTransactionModel() }
}