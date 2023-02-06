package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

class GetProductsPriceUseCase(
    private val productsDiscounts: UseCase<List<Product>, List<Discount>>
) : UseCase<List<Product>, Double> {

    override suspend fun execute(p: List<Product>): Double {
        val price = p.sumOf { it.price * it.cant }
        val discounts = productsDiscounts.execute(p).sumOf { it.discount }
        return price - discounts
    }
}
