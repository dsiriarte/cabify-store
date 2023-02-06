package com.davidsantiagoiriarte.cabifystore.views.checkoutscreen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidsantiagoiriarte.cabifystore.util.convertToEurFormat
import com.davidsantiagoiriarte.domain.model.Discount
import com.davidsantiagoiriarte.domain.model.Product

@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    val products by checkoutViewModel.products.collectAsState()

    val discounts by checkoutViewModel.discounts.collectAsState()

    val total by checkoutViewModel.total.collectAsState()

    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.scrollable(state = scrollState, orientation = Orientation.Vertical)
        ) {
            Text(
                text = "Cabify Store",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(16.dp)
            )
            Column(horizontalAlignment = Alignment.End) {

                Text(
                    text = "Checkout",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.padding(16.dp)
                )

                ProductsSummary(products = products)

                Text(
                    text = "Total Products ${
                        products.sumOf { it.price * it.cant }.convertToEurFormat()
                    }",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(16.dp)
                )

                DiscountsSummary(discounts = discounts)

                Text(
                    text = "Total Discounts ${
                        discounts.sumOf { it.discount }.convertToEurFormat()
                    }",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = "Total ${total.convertToEurFormat()}",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Button(
                onClick = {
                    checkoutViewModel.resetProducts()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(text = "Finish", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Composable
fun ProductsSummary(products: List<Product>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.height(220.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Unit : ${product.price.convertToEurFormat()}",
                style = MaterialTheme.typography.h6,
            )
        }
        Row {
            Text(
                text = "Cant: " + product.cant,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Total: " + (product.cant * product.price).convertToEurFormat(),
                style = MaterialTheme.typography.h6
            )
        }
    }
}


@Composable
fun DiscountsSummary(discounts: List<Discount>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(100.dp)
    ) {
        items(discounts) { discount ->
            DiscountItem(discount = discount)
        }
    }
}

@Composable
fun DiscountItem(discount: Discount) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = discount.description,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "-${discount.discount.convertToEurFormat()}",
            style = MaterialTheme.typography.h6
        )
    }
}
    