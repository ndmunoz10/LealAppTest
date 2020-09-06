package com.nicdamun.lealapp.mappers

import com.nicdamun.lealapp.database.entities.CommerceEntity
import com.nicdamun.lealapp.dtos.CommerceDTO
import com.nicdamun.lealapp.models.CommerceModel

fun CommerceDTO.toCommerceModel(): CommerceModel {
    return CommerceModel(
        name = name.orEmpty(),
        valueToPoints = valueToPoints ?: 0,
        branches = (branches ?: emptyList()).toBranchModels()
    )
}

fun CommerceDTO.toCommerceEntity(): CommerceEntity {
    return CommerceEntity(
        id = id,
        name = name,
        valueToPoints = valueToPoints,
        branches = branches?.toBranchEntities() ?: emptyList(),
    )
}

fun CommerceEntity.toCommerceModel(): CommerceModel {
    return CommerceModel(
        name = name.orEmpty(),
        valueToPoints = valueToPoints ?: 0,
        branches = branches.fromBranchEntitiesToBranchModels()
    )
}