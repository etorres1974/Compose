package br.com.vendas.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.vendas.ui.theme.Sizes.P
import br.com.vendas.ui.utils.MultiplePermission
import br.com.vendas.ui.utils.Permission
import br.com.vendas.ui.utils.camera.CameraPreview
import br.com.vendas.ui.utils.permissionNotAvaiable

@Composable
fun NewProducts(navController: NavHostController) {
    Column(modifier = Modifier.padding(P) ) {
        MainContent()
        Text(text = "AAAAAAAAAAAAAA")
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Permission(
        permission = android.Manifest.permission.CAMERA,
        rationale = "Permissão da Camera é necessária para adicionar novos produtos.",
        permissionNotAvailableContent = { permissionNotAvaiable(modifier = modifier, context = context) }
    ) {
        Text("It worked!")
        CameraPreview(
            modifier = modifier,
        )
    }
}