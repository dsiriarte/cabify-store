package com.davidsantiagoiriarte.cabifystore.views.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.cabifystore.di.NAMED_DECREASE_PRODUCT_CANT_USE_CASE
import com.davidsantiagoiriarte.cabifystore.di.NAMED_INCREMENT_PRODUCT_CANT_USE_CASE
import com.davidsantiagoiriarte.cabifystore.di.NAMED_SYNC_PRODUCTS_USE_CASE
import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named(NAMED_SYNC_PRODUCTS_USE_CASE)
    private val syncProductsUseCase: UseCase<Unit, Unit>,
    private val getProductsUseCase: UseCase<Unit, Flow<List<Product>>>,
    @Named(NAMED_INCREMENT_PRODUCT_CANT_USE_CASE)
    private val incrementProductCantUseCase: UseCase<Product, Unit>,
    @Named(NAMED_DECREASE_PRODUCT_CANT_USE_CASE)
    private val decreaseProductCantUseCase: UseCase<Product, Unit>
) : ViewModel() {

    private var _products = MutableStateFlow(listOf<Product>())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        sync()
    }

    private fun sync() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                syncProductsUseCase.execute(Unit)
                getProductsUseCase.execute(Unit).collectLatest {
                    _products.value = it
                }
            }
        }
    }

    fun increaseProductCant(product: Product) {
        viewModelScope.launch {
            incrementProductCantUseCase.execute(product)
        }
    }

    fun decreaseProductCant(product: Product) {
        viewModelScope.launch {
            decreaseProductCantUseCase.execute(product)
        }
    }

}
