package br.com.vendas.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.vendas.contracts.RepositoryModel
import br.com.vendas.data.Category
import br.com.vendas.data.CategoryDao

class CategoryRepo(
    private val categoryDao: CategoryDao
) : RepositoryModel<Category>{

    override fun listLiveData(): LiveData<List<Category>> = categoryDao.getData()

    override fun postItem(item: Category) = categoryDao.insert(item)

}

class MockCategoryRepository() : RepositoryModel<Category>  {

    private val _categoryLiveData = MutableLiveData<List<Category>>().apply {
        value = mockList<Category>(100, ::Category)
    }

    override fun listLiveData(): LiveData<List<Category>> = _categoryLiveData

    override fun postItem(item: Category) {
        val list = _categoryLiveData.value ?: emptyList()
        val newList = list.toMutableList().apply { add(item) }
        _categoryLiveData.postValue(newList)
    }
}

fun <T> mockList(amount: Int, constructor : (String) -> T): List<T> = mutableListOf<T>().apply {
    for (i in 1..amount) {
        add( constructor("$i"))
        if(i <= 0)
            break
    }
}.toList()

