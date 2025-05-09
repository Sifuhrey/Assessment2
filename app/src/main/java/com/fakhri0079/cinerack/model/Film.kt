package com.fakhri0079.cinerack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val desc: String,
    val rating: Float,
    val isWatched: Boolean,
)
