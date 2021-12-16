package br.com.vendas.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import br.com.vendas.ui.theme.Sizes.P
import br.com.vendas.ui.utils.Permission
import br.com.vendas.ui.utils.camera.CameraCapture
import br.com.vendas.ui.utils.camera.EMPTY_IMAGE_URI
import br.com.vendas.ui.utils.camera.GallerySelect
import br.com.vendas.ui.utils.permissionNotAvaiable
import coil.compose.rememberImagePainter

@Composable
fun NewProducts(navController: NavHostController) {
    Column(modifier = Modifier.padding(P) ) {
        pseudo2()
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
        CameraCapture(
            modifier = modifier,
        )
    }
}


@Composable
fun pseudo(modifier: Modifier = Modifier) {
    val emptyImageUri = EMPTY_IMAGE_URI
    var imageUri by remember { mutableStateOf(emptyImageUri) }
    if (imageUri != emptyImageUri) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Captured image"
            )
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = emptyImageUri
                }
            ) {
                Text("Remove image")
            }
        }
    } else {
        CameraCapture(
            modifier = modifier,
            onImageFile = { file ->
                imageUri = file.toUri()
            }
        )
    }
}

@Composable
fun pseudo2(modifier: Modifier = Modifier){
    val emptyImageUri = EMPTY_IMAGE_URI
    var imageUri by remember { mutableStateOf(emptyImageUri) }
    var showGallerySelect by remember { mutableStateOf(false) }
    if (imageUri != emptyImageUri) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Captured image"
            )
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = emptyImageUri
                }
            ) {
                Text("Remove image")
            }
        }
    }
    if (showGallerySelect) {
        GallerySelect(
            modifier = modifier,
            onImageUri = { uri ->
                showGallerySelect = false
                imageUri = uri
            }
        )
    } else {
        Box(modifier = modifier) {
            CameraCapture(
                modifier = modifier,
                onImageFile = { file ->
                    imageUri = file.toUri()
                }
            )
            Button(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(4.dp),
                onClick = {
                    showGallerySelect = true
                }
            ) {
                Text("Select from Gallery")
            }
        }
    }
}