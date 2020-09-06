package com.nicdamun.lealapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicdamun.lealapp.database.daos.TransactionDao
import com.nicdamun.lealapp.database.entities.BranchEntity
import com.nicdamun.lealapp.database.entities.CommerceEntity
import com.nicdamun.lealapp.database.entities.TransactionEntity
import com.nicdamun.lealapp.database.entities.UserEntity
import com.nicdamun.lealapp.database.typeConverters.*

@Database(
    entities = [
        TransactionEntity::class,
        CommerceEntity::class,
        BranchEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BranchListTypeConverter::class,
    BranchTypeConverter::class,
    CommerceListTypeConverter::class,
    CommerceTypeConverter::class,
    TransactionTypeTypeConverter::class,
    UserTypeConverter::class
)
abstract class LealDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}