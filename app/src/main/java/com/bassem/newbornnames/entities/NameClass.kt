package com.bassem.newbornnames.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "namesTable")
data class NameClass(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    var title: String = "",
    var description: String = "",
    var isFavorite: Boolean = false
)