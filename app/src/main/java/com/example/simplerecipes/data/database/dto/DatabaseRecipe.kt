package com.example.simplerecipes.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class DatabaseRecipe(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "source_name")
    val sourceName: String?,
    @ColumnInfo(name = "source_url")
    val sourceUrl: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val readyInMinutes: Int?,
    val summary: String?
)
