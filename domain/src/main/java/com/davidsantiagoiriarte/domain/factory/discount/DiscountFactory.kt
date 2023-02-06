package com.davidsantiagoiriarte.domain.factory.discount

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

interface DiscountFactory {

    fun doesDiscountApplyForProduct(product: Product): Boolean

    fun calculateDiscount(product: Product): Discount
}
