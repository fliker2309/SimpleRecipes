package com.example.simplerecipes.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItem(
    val type: String,
    val name: String,
    val imageUrl: String
) : Parcelable
