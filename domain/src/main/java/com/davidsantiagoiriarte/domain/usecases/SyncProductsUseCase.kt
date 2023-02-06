package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.repositories.ProductRepository

class SyncProductsUseCase(private val productRepository: ProductRepository) : UseCase<Unit, Unit> {

    override suspend fun execute(p: Unit) {
        productRepository.synchronizeProducts()
    }

}
