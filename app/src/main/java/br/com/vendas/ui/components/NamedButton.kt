package br.com.vendas.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.theme.Sizes.P

@Composable
fun NamedButton(name : String, onClick : () -> Unit) {
    Button(
        modifier = Modifier.padding(P),
        onClick = onClick){
        Text(text = name)
    }
}
