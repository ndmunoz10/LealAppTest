package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicdamun.lealapp.database.entities.CommerceEntity

class CommerceListTypeConverter {
    @TypeConverter
    fun stringToCommerce(json: String): List<CommerceEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<CommerceEntity>>() {
        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun commerceToString(list: List<CommerceEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CommerceEntity>>() {
        }.type
        return gson.toJson(list, type)
    }
}