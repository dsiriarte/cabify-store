package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val productRepository: ProductRepository) :
    UseCase<Unit, Flow<List<Product>>> {

    override suspend fun execute(p: Unit): Flow<List<Product>> {
        return productRepository.getProducts()
    }

}
