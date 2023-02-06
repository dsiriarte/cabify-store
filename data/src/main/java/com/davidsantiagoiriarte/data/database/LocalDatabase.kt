package com.davidsantiagoiriarte.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidsantiagoiriarte.data.database.daos.ProductDao
import com.davidsantiagoiriarte.data.database.entities.DBProduct

@Database(entities = [DBProduct::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
