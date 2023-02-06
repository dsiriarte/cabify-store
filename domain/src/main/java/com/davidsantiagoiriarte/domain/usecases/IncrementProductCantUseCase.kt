package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.repositories.ProductRepository

class IncrementProductCantUseCase(private val productRepository: ProductRepository) :
    UseCase<Product, Unit> {

    override suspend fun execute(p: Product) {
        productRepository.incrementProductCant(p)
    }

}
