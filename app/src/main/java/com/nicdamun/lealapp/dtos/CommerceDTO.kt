package com.nicdamun.lealapp.dtos

data class CommerceDTO (
    val id: Int?,
    val name: String?,
    val valueToPoints: Int?,
    val branches: List<BranchDTO>?
)