package br.com.vendas.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.vendas.category.Category
import br.com.vendas.category.CategoryDao
import br.com.vendas.contracts.RepositoryModel
import br.com.vendas.contracts.mockList

class MockProductRepo() : RepositoryModel<Product> {

    private val _productsLiveData = MutableLiveData<List<Product>>().apply {
        value = mockList<Product>(100, ::Product)
    }

    override fun listLiveData(): LiveData<List<Product>> = _productsLiveData

    override fun postItem(item: Product) {
        val list = _productsLiveData.value ?: emptyList()
        val newList = list.toMutableList().apply { add(item) }
        _productsLiveData.postValue(newList)
    }
}


class ProductRepo(
    private val productDao: ProductDao
) : RepositoryModel<Product>{

    override fun listLiveData(): LiveData<List<Product>> = productDao.getData()

    override fun postItem(item: Product) = productDao.insert(item)

}





