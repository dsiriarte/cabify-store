package com.davidsantiagoiriarte.cabifystore.di

import com.davidsantiagoiriarte.domain.factory.discount.DiscountFactory
import com.davidsantiagoiriarte.domain.factory.discount.TShirtDiscountFactory
import com.davidsantiagoiriarte.domain.factory.discount.VoucherDiscountFactory
import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.repositories.ProductRepository
import com.davidsantiagoiriarte.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Named(NAMED_DECREASE_PRODUCT_CANT_USE_CASE)
    @Provides
    @Singleton
    fun provideDecreaseProductCantUseCase(productRepository: ProductRepository): UseCase<Product, Unit> {
        return DecreaseProductCantUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsDiscountsUseCase(): UseCase<List<Product>, List<Discount>> {
        return GetProductsDiscountsUseCase(
            listOf(
                VoucherDiscountFactory(),
                TShirtDiscountFactory()
            )
        )
    }

    @Provides
    @Singleton
    fun provideGetProductsPriceUseCase(getProductsDiscountsUseCase: UseCase<List<Product>, List<Discount>>): UseCase<List<Product>, Double> {
        return GetProductsPriceUseCase(getProductsDiscountsUseCase)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(productRepository: ProductRepository): UseCase<Unit, Flow<List<Product>>> {
        return GetProductsUseCase(productRepository)
    }

    @Named(NAMED_INCREMENT_PRODUCT_CANT_USE_CASE)
    @Provides
    @Singleton
    fun provideIncrementProductCantUseCase(productRepository: ProductRepository): UseCase<Product, Unit> {
        return IncrementProductCantUseCase(productRepository)
    }


    @Named(NAMED_SYNC_PRODUCTS_USE_CASE)
    @Provides
    @Singleton
    fun provideSyncProductsUseCase(productRepository: ProductRepository): UseCase<Unit, Unit> {
        return SyncProductsUseCase(productRepository)
    }


    @Named(NAMED_RESET_PRODUCTS_USE_CASE)
    @Provides
    @Singleton
    fun provideRESETProductsUseCase(productRepository: ProductRepository): UseCase<Unit, Unit> {
        return ResetProductsUseCase(productRepository)
    }

}

const val NAMED_DECREASE_PRODUCT_CANT_USE_CASE = "NAMED_DECREASE_PRODUCT_CANT_USE_CASE"
const val NAMED_INCREMENT_PRODUCT_CANT_USE_CASE = "NAMED_INCREMENT_PRODUCT_CANT_USE_CASE"
const val NAMED_SYNC_PRODUCTS_USE_CASE = "NAMED_SYNC_PRODUCTS_USE_CASE"
const val NAMED_RESET_PRODUCTS_USE_CASE = "NAMED_RESET_PRODUCTS_USE_CASE"
