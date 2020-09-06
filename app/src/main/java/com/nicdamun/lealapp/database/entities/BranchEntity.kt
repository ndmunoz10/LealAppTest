package com.nicdamun.lealapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "branch",
    foreignKeys = [
        ForeignKey(
            entity = CommerceEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class BranchEntity (
    @PrimaryKey val id: Int? = -1,
    val name: String? = ""
)