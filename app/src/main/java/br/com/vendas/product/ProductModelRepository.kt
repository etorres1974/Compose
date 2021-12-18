package br.com.vendas.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.vendas.category.mockList
import br.com.vendas.contracts.RepositoryModel

class ProductModelRepository() : RepositoryModel<ProductModel> {

    private val _productsLiveData = MutableLiveData<List<ProductModel>>().apply {
        value = mockList<ProductModel>(100, ::MockProduct)
    }

    override fun listLiveData(): LiveData<List<ProductModel>> = _productsLiveData

    override fun postItem(item: ProductModel) {
        val list = _productsLiveData.value ?: emptyList()
        val newList = list.toMutableList().apply { add(item) }
        _productsLiveData.postValue(newList)
    }
}



