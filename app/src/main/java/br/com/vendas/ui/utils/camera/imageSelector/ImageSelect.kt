package br.com.vendas.ui.utils.camera.imageSelector

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.vendas.ui.utils.camera.CameraSelect
import br.com.vendas.ui.utils.camera.EMPTY_IMAGE_URI
import br.com.vendas.ui.utils.camera.GallerySelect

@Composable
fun imageSelector(modifier: Modifier = Modifier) {
    val emptyImageUri = EMPTY_IMAGE_URI
    var imageUri by remember { mutableStateOf(emptyImageUri) }
    var showGallerySelect by remember { mutableStateOf(false) }
    var openCameraSelect by remember { mutableStateOf(false) }
    imageHolder(
        modifier = modifier,
        imageUri = imageUri,
        remove = { imageUri = EMPTY_IMAGE_URI },
        gallery = {
            showGallerySelect = true
            openCameraSelect = false
        },
        camera = {
            openCameraSelect = true
            showGallerySelect = false
        }
    )
    when {
        showGallerySelect -> {
            GallerySelect() { uri ->
                showGallerySelect = false
                imageUri = uri
            }
        }
        openCameraSelect -> {
            CameraSelect() {
                openCameraSelect = false
                imageUri = it
            }
        }
    }
}




