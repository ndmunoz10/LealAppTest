package com.nicdamun.lealapp.models

enum class TransactionType {
    Opened, Unopened
}

data class TransactionModel (
    val id: Int,
    val userId: Int,
    val transactionType: TransactionType,
    val createdDate: String,
    val commerce: CommerceModel?,
    val branchModel: BranchModel?,
    val user: UserModel? = null
)