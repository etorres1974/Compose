package br.com.vendas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.vendas.MainViewModel
import br.com.vendas.ui.theme.Sizes.P

@Composable
fun Home(navController: NavHostController, mainViewModel: MainViewModel = viewModel()) {
    Column(Modifier.padding(P)) {
       Text("HOME")
    }
}