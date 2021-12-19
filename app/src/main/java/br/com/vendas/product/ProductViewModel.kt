package br.com.vendas.product


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.vendas.ui.components.TextInputStateOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    val productRepo: ProductRepo,
    application: Application
) : AndroidViewModel(application){

    val productLivedata = productRepo.listLiveData()
    val productState = TextInputStateOwner("Produto")

    fun saveNewProduct()= viewModelScope.launch(Dispatchers.IO) {
        val newCategory = Product(productState.value)
        productRepo.postItem(newCategory)
        productState.onValueChanged("")
    }
}