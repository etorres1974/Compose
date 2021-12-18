package br.com.vendas.category


import android.app.Application
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.vendas.data.Category
import br.com.vendas.ui.components.TextInputStateOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(
    val categoryRepo: CategoryRepo,
    application: Application
) : AndroidViewModel(application){

    val categoryLivedata = categoryRepo.listLiveData()
    val categoryState = TextInputStateOwner("Categoria")

    fun saveNewCategory()= viewModelScope.launch(Dispatchers.IO) {
        val newCategory = Category(categoryState.value)
        categoryRepo.postItem(newCategory)
        categoryState.onValueChanged("")
    }
}