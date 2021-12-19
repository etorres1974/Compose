package br.com.vendas.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.vendas.contracts.RepositoryModel
import br.com.vendas.contracts.mockList

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



