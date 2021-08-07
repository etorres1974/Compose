package br.com.vendas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.vendas.ui.components.CustomSlider
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.theme.Sizes.P
import br.com.vendas.ui.theme.recomposeOnChange

@Composable
fun Settings(navController: NavHostController) {
    Column(modifier = Modifier.padding(P)) {
        SettingSlider()
    }
}

@Composable
fun SettingSlider(size: Sizes = Sizes) {
    var id by remember { mutableStateOf(size.id) }
    CustomSlider(name = "Base", initialValue = size.base.toFloat(), valueRange = 1f..10f) {
        id = size.update(base = it.toDouble())
    }
    CustomSlider(name = "Razao", initialValue = size.razao.toFloat(), valueRange = 1f..2f) {
        id = size.update(razao = it.toDouble())
    }
    id.recomposeOnChange {
        PaddingExample(Modifier, size)
    }
}

@Composable
fun PaddingExample(modifier: Modifier, size: Sizes) {
    Text(modifier = modifier.padding(size.PP), text = "PP : ${size.PP}")
    Text(modifier = modifier.padding(size.P), text = "P : ${size.P}")
    Text(modifier = modifier.padding(size.M), text = "M : ${size.M}")
    Text(modifier = modifier.padding(size.G), text = "G : ${size.G}")
    Text(modifier = modifier.padding(size.GG), text = "GG : ${size.GG}")
}

