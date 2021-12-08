package br.com.vendas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.vendas.model.ProductModel
import br.com.vendas.ui.theme.Sizes.G
import br.com.vendas.ui.theme.Sizes.GG
import br.com.vendas.ui.theme.Sizes.P
import br.com.vendas.ui.theme.Sizes.PP
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import localDataSource.mockList

@ExperimentalCoilApi
@Composable
fun Product(product: ProductModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = product.image),
            contentDescription = "Android Logo",
            modifier = Modifier.size(GG).padding(PP)
        )
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