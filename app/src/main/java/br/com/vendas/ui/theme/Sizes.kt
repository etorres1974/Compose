package br.com.vendas.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*
import kotlin.math.roundToInt

object Sizes {
    var id = UUID.randomUUID()
    var razao: Double = 1.61803398875
    var base: Double = Math.PI
    var PP = (base * razao).roundToInt().dp
    var P = PP.next()
    var M = P.next()
    var G = M.next()
    var GG = G.next()

    fun update(razao: Double = this.razao, base: Double = this.base): UUID? {
         this.razao = razao
         this.base = base
         PP = (base * razao).roundToInt().dp
         P = PP.next()
         M = P.next()
         G = M.next()
         GG = G.next()
         var id = UUID.randomUUID()
         return id
    }

    fun Dp.next() = (this.value * razao).roundToInt().dp
}

@Composable
fun UUID?.recomposeOnChange(content: @Composable () -> Unit) {
    if (this != null)
        content()
}


