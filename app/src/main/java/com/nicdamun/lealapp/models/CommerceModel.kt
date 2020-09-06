package com.nicdamun.lealapp.models

data class CommerceModel (
    val name: String,
    val valueToPoints: Int,
    val branches: List<BranchModel>
)