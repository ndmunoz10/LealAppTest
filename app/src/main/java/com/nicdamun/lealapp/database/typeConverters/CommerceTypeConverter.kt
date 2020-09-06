package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicdamun.lealapp.database.entities.CommerceEntity

class CommerceTypeConverter {
    @TypeConverter
    fun stringToCommerce(json: String): CommerceEntity {
        val gson = Gson()
        val type = object : TypeToken<CommerceEntity>() {
        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun commerceToString(list: CommerceEntity): String {
        val gson = Gson()
        val type = object : TypeToken<CommerceEntity>() {
        }.type
        return gson.toJson(list, type)
    }
}