package br.com.vendas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import br.com.vendas.model.ProductModel
import br.com.vendas.ui.components.Product
import br.com.vendas.ui.components.ProductList
import br.com.vendas.ui.theme.Constants.Sizes
import br.com.vendas.ui.theme.VendaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendaApp {
                Column {
                    ProductList(list = ProductModel.mockList(100))
                }
            }
        }
        Log.d("SIZES", "${Sizes}")
    }
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
                    Product(product = ProductModel.Companion.MockProduct())
                }
            }
        }
    }
}

