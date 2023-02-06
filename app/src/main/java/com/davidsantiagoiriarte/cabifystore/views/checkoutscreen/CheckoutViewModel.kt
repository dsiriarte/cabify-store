package com.davidsantiagoiriarte.cabifystore.views.checkoutscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.cabifystore.di.NAMED_RESET_PRODUCTS_USE_CASE
import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product
import com.davidsantiagoiriarte.domain.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val getProductsUseCase: UseCase<Unit, Flow<List<Product>>>,
    private val getProductsDiscountsUseCase: UseCase<List<Product>, List<Discount>>,
    @Named(NAMED_RESET_PRODUCTS_USE_CASE)
    private val resetProductsUseCase: UseCase<Unit, Unit>
) : ViewModel() {

    private var _products = MutableStateFlow(listOf<Product>())
    val products: StateFlow<List<Product>> = _products.asStateFlow()


    private var _discounts = MutableStateFlow(listOf<Discount>())
    val discounts: StateFlow<List<Discount>> = _discounts.asStateFlow()


    private var _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getProductsUseCase.execute(Unit).collectLatest { products ->
                    val filteredProducts = products.filter { it.cant > 0 }
                    _products.value = filteredProducts
                    val discounts = getProductsDiscountsUseCase.execute(filteredProducts)
                    _discounts.value = discounts
                    _total.value =
                        filteredProducts.sumOf { it.price * it.cant } - discounts.sumOf { it.discount }
                }
            }
        }
    }

    fun resetProducts(){
        viewModelScope.launch {
            resetProductsUseCase.execute(Unit)
        }
    }

}
