package br.com.vendas.ui.utils.camera.imageSelector

import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.vendas.ui.screens.imageHolder
import br.com.vendas.ui.utils.camera.CameraSelect
import br.com.vendas.ui.utils.camera.EMPTY_IMAGE_URI
import br.com.vendas.ui.utils.camera.GallerySelect

//data class ImageSelectorState(
//    var imageUri : Uri = EMPTY_IMAGE_URI,
//    var showGallerySelect: Boolean = false,
//    var openCameraSelect : Boolean = false,
//){
//    fun closeSelector(uri: Uri = EMPTY_IMAGE_URI): ImageSelectorState {
//        showGallerySelect = false
//        openCameraSelect = false
//        return this
//    }
//
//    fun openGallery() : ImageSelectorState{
//        showGallerySelect = true
//        openCameraSelect = false
//        return this
//    }
//    fun openCamera(): ImageSelectorState{
//        openCameraSelect = true
//        showGallerySelect = false
//        return this
//    }
//    fun clearImage(): ImageSelectorState{
//        imageUri = EMPTY_IMAGE_URI
//        return this
//    }
//}
//
//@Composable
//fun imageSelector(modifier: Modifier = Modifier, state : ImageSelectorState, onChange : (ImageSelectorState) -> Unit) {
//    imageHolder(
//        modifier = modifier,
//        imageUri = state.imageUri,
//        remove =  { onChange(state.clearImage()) },
//        gallery = { onChange(state.openGallery()) },
//        camera = {  onChange(state.openCamera()) }
//    )
//    with(state) {
//        when {
//            showGallerySelect -> GallerySelect() { uri -> closeSelector(uri) }
//            openCameraSelect -> CameraSelect() { uri -> closeSelector(uri) }
//        }
//    }
//}

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
