package br.com.vendas.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

@Composable
fun TextInput(stateOwner : TextInputStateOwner) {
    TextField(
        label = { Text(stateOwner.label) },
        value = stateOwner.value,
        onValueChange = { stateOwner.onValueChanged(it) }
    )
}

class TextInputStateOwner(val label: String) : MutableStateOwner {
    private val _mutableState  = mutableStateOf("")
    val value get() = _mutableState.value
    override fun getMutableState() : MutableState<String> = _mutableState
    override fun onValueChanged(newValue : String) {
        _mutableState.value = newValue
    }
}

interface MutableStateOwner {
    fun getMutableState() : MutableState<String>
    fun onValueChanged(newValue : String)
}
