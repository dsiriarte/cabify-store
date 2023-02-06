package com.davidsantiagoiriarte.cabifystore.util

import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.*

fun Any.toJson(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    return Gson().fromJson(this, T::class.java)
}

val fakeProducts = listOf(
    Product("VOUCHER", "Cabify Voucher", 5.0, 0),
    Product("TSHIRT", "Cabify T-Shirt", 20.0, 2),
    Product("MUG", "Cabify Coffee Mug", 7.5, 1)

)
val fakeDiscounts = listOf(
    Discount("Discount Cabify VOUCHER", 10.0),
    Discount("Discount Cabify T-Shirt", 2.0)
)

fun Double.convertToEurFormat(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("EUR")
    return format.format(this)
}
