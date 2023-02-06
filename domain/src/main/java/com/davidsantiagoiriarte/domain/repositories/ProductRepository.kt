package com.davidsantiagoiriarte.domain.repositories

import com.davidsantiagoiriarte.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun synchronizeProducts()
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun incrementProductCant(product: Product)
    suspend fun decreaseProductCant(product: Product)
    suspend fun setProductsCantToZero()

}
