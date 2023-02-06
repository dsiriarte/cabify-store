package com.davidsantiagoiriarte.data.repositories

import com.davidsantiagoiriarte.data.database.daos.ProductDao
import com.davidsantiagoiriarte.data.network.ProductService
import com.davidsantiagoiriarte.data.util.map
import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val productService: ProductService
) : ProductRepository {

    override suspend fun synchronizeProducts() {
        val networkProductsResponse = productService.getProducts()
        productDao.insertAll(networkProductsResponse.map())
    }

    override suspend fun getProducts(): Flow<List<Product>> {
        return productDao.getAll().map { dbProductList -> dbProductList.map { it.map() } }
    }

    override suspend fun incrementProductCant(product: Product) {
        productDao.update(product.map(product.cant + 1))
    }

    override suspend fun decreaseProductCant(product: Product) {
        productDao.update(product.map(product.cant - 1))
    }

    override suspend fun setProductsCantToZero() {
        productDao.setProductsCantToZero()
    }
}
