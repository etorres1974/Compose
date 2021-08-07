package br.com.vendas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import br.com.vendas.model.ProductModel
import localDataSource.mockList

@Composable
fun Product(product: ProductModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "${product.name}")
    }
}

@Composable
fun ProductList(list: List<ProductModel>) {
    ScrollingListLarge(list = list) {
        Product(product = it)
    }
}

@Composable
fun ProductList(state: State<List<ProductModel>>) {
    val list by state
    ProductList(list = list)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductList(list = mockList(5))
}