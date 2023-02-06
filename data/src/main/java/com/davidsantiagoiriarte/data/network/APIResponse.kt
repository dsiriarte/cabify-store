package com.davidsantiagoiriarte.data.network

data class APIResponse(val products : List<APIProduct>)
data class APIProduct(val code: String, val name: String, val price: Double)
