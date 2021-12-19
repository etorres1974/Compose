package br.com.vendas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.theme.VendaTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendaApp {
                BottomNavBar()
            }
        }
        Log.d("SIZES", "$Sizes")
    }


    @Composable
    fun VendaApp(content: @Composable () -> Unit) {
        VendaTheme {
            Surface(
                modifier = Modifier.padding(Sizes.P),
                color = MaterialTheme.colors.background) {
                content()
            }
        }
    }
}

