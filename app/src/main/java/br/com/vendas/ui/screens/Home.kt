package br.com.vendas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.vendas.model.ProductModel
import br.com.vendas.ui.components.ProductList

@Composable
fun Home(navController: NavHostController) {
    Text(text = "HOME")
    Column {
        ProductList(list = ProductModel.mockList(100))
    }
}