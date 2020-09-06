package com.nicdamun.lealapp.mappers

import com.nicdamun.lealapp.database.entities.BranchEntity
import com.nicdamun.lealapp.dtos.BranchDTO
import com.nicdamun.lealapp.models.BranchModel

fun BranchDTO.toBranchModel(): BranchModel {
    return BranchModel(
        name = name.orEmpty()
    )
}

fun List<BranchDTO>.toBranchModels(): List<BranchModel> {
    return this.map { branchDTO -> branchDTO.toBranchModel() }
}

fun BranchDTO.toBranchEntity(): BranchEntity {
    return BranchEntity(
        name = name.orEmpty()
    )
}

fun List<BranchDTO>.toBranchEntities(): List<BranchEntity> {
    return this.map { branchDTO -> branchDTO.toBranchEntity() }
}

fun BranchEntity.toBranchModel(): BranchModel {
    return BranchModel(
        name = name.orEmpty()
    )
}

fun List<BranchEntity>.fromBranchEntitiesToBranchModels(): List<BranchModel> {
    return this.map { branchEntity -> branchEntity.toBranchModel() }
}