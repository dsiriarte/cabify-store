package com.davidsantiagoiriarte.data.network

import retrofit2.http.GET

interface ProductService {

    @GET("Products.json")
    suspend fun getProducts(): APIResponse
}
