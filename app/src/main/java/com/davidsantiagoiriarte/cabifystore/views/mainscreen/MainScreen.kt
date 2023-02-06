package com.davidsantiagoiriarte.cabifystore.views.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.davidsantiagoiriarte.cabifystore.navigation.Screen
import com.davidsantiagoiriarte.cabifystore.ui.theme.CabifyStoreTheme
import com.davidsantiagoiriarte.cabifystore.util.fakeProducts
import com.davidsantiagoiriarte.domain.model.Product
import javax.inject.Inject

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val products by mainViewModel.products.collectAsState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Cabify Store",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(16.dp)
                )
                ItemList(
                    products = products,
                    mainViewModel
                )
            }
            Button(
                onClick = {
                    if (products.sumOf { it.cant } > 0) {
                        navController.navigate(route = Screen.CheckoutScreen.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(text = "Checkout", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Composable
fun ItemList(products: List<Product>, mainViewModel: MainViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(products) { product ->
            Item(product, mainViewModel)
        }
    }
}

@Composable
fun Item(product: Product, mainViewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .padding(16.dp)
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
                text = "${product.price}€",
                style = MaterialTheme.typography.h6,
            )
        }
        Row {
            if (product.cant > 0) {
                Button(onClick = {
                    mainViewModel.decreaseProductCant(
                        product
                    )
                }, modifier = Modifier.size(40.dp)) {
                    Text(text = "-")
                }
            }
            Text(
                text = "" + product.cant,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Button(
                onClick = { mainViewModel.increaseProductCant(product) },
                modifier = Modifier.size(40.dp)
            ) {
                Text(text = "+")
            }
        }
    }
}
