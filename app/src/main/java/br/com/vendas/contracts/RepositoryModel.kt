package br.com.vendas.contracts

import androidx.lifecycle.LiveData

interface RepositoryModel<T> {
    fun listLiveData() : LiveData<List<T>>
    fun postItem(item : T)
}