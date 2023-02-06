package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.factory.discount.DiscountFactory
import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

class GetProductsDiscountsUseCase(private val discountFactories: List<DiscountFactory>) :
    UseCase<List<Product>, List<Discount>> {

    override suspend fun execute(p: List<Product>): List<Discount> {
        val discounts = arrayListOf<Discount>()
        for (product in p) {
            for (factory in discountFactories) {
                if (factory.doesDiscountApplyForProduct(product)) {
                    discounts.add(factory.calculateDiscount(product))
                }
            }
        }
        return discounts
    }

}
