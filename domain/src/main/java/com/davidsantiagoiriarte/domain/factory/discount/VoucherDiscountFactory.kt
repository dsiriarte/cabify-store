package com.davidsantiagoiriarte.domain.factory.discount

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

class VoucherDiscountFactory : DiscountFactory {

    override fun doesDiscountApplyForProduct(product: Product): Boolean {
        return product.code == "VOUCHER" && product.cant > 1
    }

    override fun calculateDiscount(product: Product): Discount {
        val vouchersPrice = product.price
        val mod = product.cant % 2
        return if (mod == 0) {
            Discount.VoucherDiscount(
                vouchersPrice * (product.cant / 2)
            )
        } else {
            Discount.VoucherDiscount(
                vouchersPrice * ((product.cant - 1) / 2)
            )
        }
    }
}
