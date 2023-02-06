package com.davidsantiagoiriarte.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBProduct(
    @PrimaryKey val code: String,
    val name: String,
    val price: Double,
    val cant: Int = 0
)
