package com.nicdamun.lealapp.dtos

data class TransactionDTO (
    val id: Int?,
    val userId: Int?,
    val createdDate: String?,
    val commerce: CommerceDTO?,
    val branch: BranchDTO?
)