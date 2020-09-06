package com.nicdamun.lealapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "commerce",
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class CommerceEntity (
    @PrimaryKey val id: Int? = -1,
    val name: String? = "",
    val valueToPoints: Int? = -1,
    val branches: List<BranchEntity> = emptyList()
)