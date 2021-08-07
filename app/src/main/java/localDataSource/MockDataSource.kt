package localDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.vendas.contracts.LocalDataContract
import br.com.vendas.model.ProductModel
import java.util.*

class MockDataSource() : LocalDataContract {
    private val _productsLiveData = MutableLiveData<List<ProductModel>>().apply {
        value = mockList(100)
    }
    override val productLiveData: LiveData<List<ProductModel>> = _productsLiveData
}

data class MockProduct(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String = "Mock"
) : ProductModel

fun mockList(amount: Int): List<ProductModel> = mutableListOf<ProductModel>().apply {
    for (i in 1..amount) {
        add(MockProduct())
        if(i <= 0)
            break
    }
}.toList()