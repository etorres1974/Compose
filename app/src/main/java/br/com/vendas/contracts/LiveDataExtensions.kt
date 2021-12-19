package br.com.vendas.contracts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData

@Composable
fun <T> LiveData<T>.toMutableState() = this.observeAsState(emptyList<T>())

@Composable
fun <T> LiveData<List<T>>.toMutableStateList() = observeAsState(emptyList())

fun <T> mockList(amount: Int, constructor : (String) -> T): List<T> = mutableListOf<T>().apply {
    for (i in 1..amount) {
        add( constructor("$i"))
        if(i <= 0)
            break
    }
}.toList()
