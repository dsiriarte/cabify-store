package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

class GetProductsDiscountsUseCase : UseCase<List<Product>, List<Discount>> {

    override suspend fun execute(p: List<Product>): List<Discount> {
        val vouchers = p.firstOrNull { it.code == "VOUCHER" }
        val tshirts = p.firstOrNull { it.code == "TSHIRT" }
        val discounts = arrayListOf<Discount>()
        if (tshirts != null && tshirts.cant >= 3) {
            val tshritsDiscount = tshirts.price - 19.0
            discounts.add(Discount("Cabify T-Shirt 3+ promotion", tshritsDiscount * tshirts.cant))
        }
        vouchers?.let {
            if (vouchers.cant > 1) {
                val vouchersPrice = vouchers.price
                val mod = vouchers.cant % 2
                if (mod == 0) {
                    discounts.add(
                        Discount(
                            "Cabify Voucher 2-for-1",
                            vouchersPrice * (vouchers.cant / 2)
                        )
                    )
                } else {
                    discounts.add(
                        Discount(
                            "Cabify Voucher 2-for-1",
                            vouchersPrice * ((vouchers.cant - 1) / 2)
                        )
                    )
                }
            }
        }

        return discounts
    }

}
