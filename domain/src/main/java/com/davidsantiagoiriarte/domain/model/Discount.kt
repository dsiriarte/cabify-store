package com.davidsantiagoiriarte.domain.model

sealed class Discount(val description: String, val discount: Double) {
    class VoucherDiscount(discount: Double) : Discount(VOUCHER_DISCOUNT_DESCRIPTION, discount)
    class TShirtDiscount(discount: Double) : Discount(T_SHIRT_DISCOUNT_DESCRIPTION, discount)
}

const val VOUCHER_DISCOUNT_DESCRIPTION = "Cabify Voucher 2-for-1"
const val T_SHIRT_DISCOUNT_DESCRIPTION = "Cabify T-Shirt 3+ promotion"
