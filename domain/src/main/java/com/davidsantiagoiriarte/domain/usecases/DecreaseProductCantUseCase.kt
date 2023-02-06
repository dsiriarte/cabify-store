package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.repositories.ProductRepository

class DecreaseProductCantUseCase(private val productRepository: ProductRepository) :
    UseCase<Product, Unit> {

    override suspend fun execute(p: Product) {
        productRepository.decreaseProductCant(p)
    }

}
