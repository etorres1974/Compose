package br.com.vendas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
    LazyColumn(state = scrollState) {
        items(list) {
            content(it)
            Spacer(Modifier.height(P))
        }
    }
}

@Composable
fun <T> ScrollingListLarge(list: List<T>, content: @Composable (T) -> Unit) {
    val scrollState = rememberLazyListState()
    Column {
        Row {
            CoroutineButton(action = scrollState::scrollToStart, buttonText = "Start")
            CoroutineButton(action = { scrollState.scrollToEnd(list) }, buttonText = "End")
        }

        ScrollingList(list = list, scrollState) {
            content(it)
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

