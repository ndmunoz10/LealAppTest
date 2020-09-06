package com.nicdamun.lealapp.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicdamun.lealapp.database.entities.BranchEntity

class BranchListTypeConverter {
    @TypeConverter
    fun stringToBranch(json: String): BranchEntity {
        val gson = Gson()
        val type = object : TypeToken<BranchEntity>() {
        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun branchToString(list: BranchEntity): String {
        val gson = Gson()
        val type = object : TypeToken<BranchEntity>() {
        }.type
        return gson.toJson(list, type)
    }
}