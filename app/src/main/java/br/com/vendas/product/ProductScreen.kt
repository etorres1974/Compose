package br.com.vendas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import br.com.vendas.category.CategoryList
import br.com.vendas.category.CreateCategory
import br.com.vendas.contracts.toMutableStateList
import br.com.vendas.product.Product
import br.com.vendas.product.ProductViewModel
import br.com.vendas.ui.utils.camera.imageSelector.imageSelector
import coil.annotation.ExperimentalCoilApi
import org.koin.androidx.compose.getViewModel



@Composable
fun ProductScreen(navHostController: NavHostController){
    CenteredColumn {
        CreateProduct()
        ProductyList()
    }
}

@Composable
fun CreateProduct(productViewModel: ProductViewModel = getViewModel<ProductViewModel>()) {
    val categoryState = productViewModel.productState
    TextInput(stateOwner = categoryState)
    NamedButton(name = "Adicionar") {
        productViewModel.saveNewProduct()
    }
}

@Composable
fun ProductyList(productViewModel: ProductViewModel = getViewModel<ProductViewModel>()) {
    val productlist = productViewModel.productLivedata.toMutableStateList()
    Text(text = "Produtos Criados :\n")
    ScrollingListLarge(productlist) {
        ProductView(product = it)
    }
}


@Composable
fun ProductView(product: Product) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = product.name)
    }
}

