package br.com.vendas


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.vendas.contracts.LocalDataContract
import localDataSource.MockDataSource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val localDataSource: LocalDataContract = MockDataSource()

    val productList = localDataSource.productLiveData
}