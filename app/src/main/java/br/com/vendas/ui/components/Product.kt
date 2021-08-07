package br.com.vendas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import br.com.vendas.VendaApp
import br.com.vendas.model.ProductModel
import br.com.vendas.ui.theme.VendaTheme

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VendaTheme {
        VendaApp {
            ProductList(list = ProductModel.mockList(100))
        }
    }
}