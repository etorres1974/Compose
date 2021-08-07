package br.com.vendas.contracts

import androidx.lifecycle.LiveData
import br.com.vendas.model.ProductModel

interface LocalDataContract {
    val productLiveData : LiveData<List<ProductModel>>
}