package br.com.vendas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.vendas.ui.theme.Sizes.P

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun <T> ScrollingList(
    list: List<T>,
    scrollState: LazyListState = rememberLazyListState(),
    content: @Composable (T) -> Unit
) {
    LazyColumn(Modifier.fillMaxWidth() ,state = scrollState) {
        items(list) {
            content(it)
            Spacer(Modifier.height(P))
        }
    }
}

@Composable
fun <T> ScrollingListLarge(list: List<T>, buttons : Boolean = false,  content: @Composable (T) -> Unit) {
    val scrollState = rememberLazyListState()
    Column {
        if(buttons) {
            Row {
                CoroutineButton(action = scrollState::scrollToStart, buttonText = "Start")
                CoroutineButton(action = { scrollState.scrollToEnd(list) }, buttonText = "End")
            }
        }
        ScrollingList(list = list, scrollState) {
            Row{
                Text("${list.indexOf(it)} - ")
                content(it)
            }
        }
    }
}

@Composable
fun <T> ScrollingListLarge(
    state : State<List<T>>,
    buttons: Boolean = false,
    reversed : Boolean = true,
    content: @Composable (T) -> Unit) {
    val list = if(reversed) state.value.reversed() else state.value
    val scrollState = rememberLazyListState()
    Column {
        if(buttons) {
            Row {
                CoroutineButton(action = scrollState::scrollToStart, buttonText = "Start")
                CoroutineButton(action = { scrollState.scrollToEnd(list) }, buttonText = "End")
            }
        }
        ScrollingList(list = list, scrollState) {
            Row{
                val index =
                    if(reversed)
                        list.size - list.indexOf(it)
                    else
                        list.indexOf(it)
                Text("$index - ")
                content(it)
            }
        }
    }
}

@Composable
private fun CoroutineButton(
    action: suspend () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    buttonText: String
) {
    Button(onClick = {
        coroutineScope.launch { action.invoke() }
    }) {
        Text(buttonText)
    }
}

private suspend fun LazyListState.scrollToStart() = animateScrollToItem(0)
private suspend fun LazyListState.scrollToEnd(list: List<*>) = animateScrollToItem(list.lastIndex)

