package com.nicdamun.lealapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val birthDay: String,
    val photo: String
)