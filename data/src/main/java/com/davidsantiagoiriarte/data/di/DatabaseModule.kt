package com.davidsantiagoiriarte.data.di

import android.content.Context
import androidx.room.Room
import com.davidsantiagoiriarte.data.database.LocalDatabase
import com.davidsantiagoiriarte.data.database.daos.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun createDatabase(@ApplicationContext applicationContext: Context): LocalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(localDatabase: LocalDatabase): ProductDao {
        return localDatabase.productDao()
    }
}

const val DATABASE_NAME = "product-database"
