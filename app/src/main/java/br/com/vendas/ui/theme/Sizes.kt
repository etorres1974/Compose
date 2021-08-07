package br.com.vendas.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt



object Constants {

    private val razao = 1.61803398875
    private val base = Math.PI
    val PP = (base * razao).roundToInt().dp
    val P = PP.next()
    val M = P.next()
    val G = M.next()
    val GG = G.next()
    val Sizes = listOf(PP, P, M, G, GG)
    fun Dp.next() = (this.value * razao).roundToInt().dp
}


