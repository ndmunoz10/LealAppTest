package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicdamun.lealapp.database.entities.BranchEntity

class BranchTypeConverter {
    @TypeConverter
    fun stringToBranch(json: String): List<BranchEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<BranchEntity>>() {
        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun branchToString(list: List<BranchEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<BranchEntity>>() {
        }.type
        return gson.toJson(list, type)
    }
}