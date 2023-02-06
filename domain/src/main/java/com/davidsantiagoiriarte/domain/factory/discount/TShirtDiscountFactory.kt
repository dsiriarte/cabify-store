package com.davidsantiagoiriarte.domain.factory.discount

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

class TShirtDiscountFactory : DiscountFactory {
    override fun doesDiscountApplyForProduct(product: Product): Boolean {
        return product.code == "TSHIRT" && product.cant >= 3
    }

    override fun calculateDiscount(product: Product): Discount {
        val discount = product.price - 19.0
        return Discount.TShirtDiscount(discount * product.cant)
    }
}
