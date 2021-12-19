package br.com.vendas.di

import br.com.vendas.MainViewModel
import br.com.vendas.category.CategoryRepo
import br.com.vendas.category.CategoryViewModel
import br.com.vendas.data.AppDataBase
import br.com.vendas.data.provideRoom
import br.com.vendas.product.ProductRepo
import br.com.vendas.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidModule() = listOf(
    databases,
    repositories,
    viewModels
)

val databases = module {
    single { provideRoom(application = get()) }
    single { get<AppDataBase>().categoryDao() }
    single { get<AppDataBase>().productDao() }
}

val repositories = module {
    single { CategoryRepo(categoryDao = get()) }
    single { ProductRepo(get()) }
}

val viewModels = module {
    viewModel { MainViewModel(application = get()) }
    viewModel { CategoryViewModel(application = get(), categoryRepo = get()) }
    viewModel { ProductViewModel(get(), get()) }
}



