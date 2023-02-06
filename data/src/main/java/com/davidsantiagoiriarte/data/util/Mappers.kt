package com.davidsantiagoiriarte.data.util

import com.davidsantiagoiriarte.data.database.entities.DBProduct
import com.davidsantiagoiriarte.data.network.APIResponse
import com.davidsantiagoiriarte.domain.model.Product

fun DBProduct.map() = Product(code, name, price, cant)

fun Product.map(newCant: Int) = DBProduct(code, name, price, newCant)

fun APIResponse.map() = products.map { DBProduct(it.code, it.name, it.price) }
