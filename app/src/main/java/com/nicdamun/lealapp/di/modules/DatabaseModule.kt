package com.nicdamun.lealapp.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicdamun.lealapp.database.LealDatabase
import com.nicdamun.lealapp.database.daos.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object DatabaseModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): LealDatabase {
        return Room.databaseBuilder(
            context,
            LealDatabase::class.java,
            "leal_db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .build()
    }

    @Provides
    fun provideTransactionDao(database: LealDatabase): TransactionDao {
        return database.transactionDao()
    }
}