package br.com.vendas.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun navigateToSettingsScreen(context: Context) =
    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    })

@Composable
fun permissionNotAvaiable(modifier : Modifier, context: Context){
    Column(modifier) {
        Text("O noes! No Camera!")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navigateToSettingsScreen(context) }) {
            Text("Open Settings")
        }
    }
}