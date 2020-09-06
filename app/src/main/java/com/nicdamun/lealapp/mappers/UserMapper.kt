package com.nicdamun.lealapp.mappers

import com.nicdamun.lealapp.database.entities.UserEntity
import com.nicdamun.lealapp.dtos.UserDTO
import com.nicdamun.lealapp.models.UserModel

fun UserDTO.toUserModel(): UserModel {
    return UserModel(
        id = id ?: -1,
        name = name.orEmpty(),
        birthDay = birthDay.orEmpty(),
        photo = photo.orEmpty()
    )
}

fun UserModel.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        birthDay = birthDay,
        photo = photo
    )
}

fun UserEntity.toUserModel(): UserModel {
    return UserModel(
        id = id,
        name = name,
        photo = photo,
        birthDay = birthDay
    )
}