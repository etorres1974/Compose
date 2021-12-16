package br.com.vendas.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.vendas.ui.theme.Sizes.P
import br.com.vendas.ui.utils.Permission
import br.com.vendas.ui.utils.camera.EMPTY_IMAGE_URI
import br.com.vendas.ui.utils.camera.imageSelector.imageSelector
import br.com.vendas.ui.utils.permissionNotAvaiable
import coil.compose.rememberImagePainter

@Composable
fun NewProducts(navController: NavHostController) {
    Column(modifier = Modifier.padding(P)) {
        MainContent()
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Permission(
        permission = android.Manifest.permission.CAMERA,
        rationale = "Permissão da Camera é necessária para adicionar novos produtos.",
        permissionNotAvailableContent = {
            permissionNotAvaiable(
                modifier = modifier,
                context = context
            )
        }
    ) {

        imageSelector()
    }
}

@Composable
fun imageHolder(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    remove: (Uri) -> Unit,
    gallery: () -> Unit,
    camera: () -> Unit
) {

    Box(modifier = modifier) {
        if (imageUri != EMPTY_IMAGE_URI) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = rememberImagePainter(imageUri),
                    contentDescription = "Captured image"
                )
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { remove(imageUri) }
                ) {
                    Text("Remove image")
                }
            }
        } else Row {
            Button(modifier = modifier.padding(P), onClick = { gallery() }
            ) {
                Text("Select from Gallery")
            }

            Button(modifier = modifier.padding(P), onClick = { camera() }
            ) {
                Text("Open Camera")
            }
        }

    }
}



