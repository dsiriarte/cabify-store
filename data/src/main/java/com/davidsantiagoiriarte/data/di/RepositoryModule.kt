package com.davidsantiagoiriarte.data.di

import com.davidsantiagoiriarte.data.database.daos.ProductDao
import com.davidsantiagoiriarte.data.network.ProductService
import com.davidsantiagoiriarte.data.repositories.ProductRepositoryImpl
import com.davidsantiagoiriarte.domain.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao,
        productService: ProductService
    ): ProductRepository {
        return ProductRepositoryImpl(productDao, productService)
    }
}
