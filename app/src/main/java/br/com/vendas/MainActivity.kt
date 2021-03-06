package br.com.vendas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.vendas.product.MockProduct
import br.com.vendas.ui.components.Product
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.theme.VendaTheme
import org.koin.androidx.compose.getViewModel
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
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        VendaTheme {
            VendaApp {
                LazyColumn {
                    item {
                        Product(product = MockProduct())
                    }
                }
            }
        }
    }
}

