package br.com.vendas


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.vendas.contracts.RepositoryModel
import br.com.vendas.product.ProductModel
import br.com.vendas.product.ProductModelRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val productsSource: RepositoryModel<ProductModel> = ProductModelRepository()

    val productList = productsSource.listLiveData()
}