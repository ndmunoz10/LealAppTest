package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicdamun.lealapp.database.entities.UserEntity

class UserTypeConverter {
    @TypeConverter
    fun stringToUser(json: String): UserEntity? {
        val gson = Gson()
        val type = object : TypeToken<UserEntity?>() {
        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun userToString(list: UserEntity?): String {
        val gson = Gson()
        val type = object : TypeToken<UserEntity?>() {
        }.type
        return gson.toJson(list, type)
    }
}