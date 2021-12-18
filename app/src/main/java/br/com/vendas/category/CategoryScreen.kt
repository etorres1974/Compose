package br.com.vendas.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.vendas.contracts.toMutableStateList
import br.com.vendas.data.Category
import br.com.vendas.ui.components.CenteredColumn
import br.com.vendas.ui.components.NamedButton
import br.com.vendas.ui.components.ScrollingListLarge
import br.com.vendas.ui.components.TextInput
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.utils.camera.imageSelector.imageSelector
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel


@Composable
fun CategoryScreen(navHostController: NavHostController){
    CenteredColumn {
        imageSelector()
        CreateCategory()
        CategoryList()
    }
}

@Composable
fun CreateCategory(categoryViewModel: CategoryViewModel = getViewModel<CategoryViewModel>()) {
    val categoryState = categoryViewModel.categoryState
    TextInput(stateOwner = categoryState)
    NamedButton(name = "Adicionar") {
        categoryViewModel.saveNewCategory()
    }
}

@Composable
fun CategoryList(categoryViewModel: CategoryViewModel = getViewModel<CategoryViewModel>()) {
    val categorylist = categoryViewModel.categoryLivedata.toMutableStateList()
    Text(text = "Categorias Criadas :\n")
    ScrollingListLarge(categorylist) {
        CategoryView(category = it)
    }
}


@Composable
fun CategoryView(category: Category) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (false) { // TODO adde Category image
            Image(
                painter = rememberImagePainter(data = category.name),
                contentDescription = "Android Logo",
                modifier = Modifier
                    .size(Sizes.GG)
                    .padding(Sizes.PP)
            )
        }
        Text(text = category.name)
    }
}





