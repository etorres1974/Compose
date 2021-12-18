package br.com.vendas.ui.utils.camera.imageSelector

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vendas.ui.theme.Sizes
import br.com.vendas.ui.utils.camera.EMPTY_IMAGE_URI
import coil.compose.rememberImagePainter


@Composable
fun imageHolder(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    remove: (Uri) -> Unit,
    gallery: () -> Unit,
    camera: () -> Unit
) {

    if (imageUri != EMPTY_IMAGE_URI) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = rememberImagePainter(imageUri),
            contentDescription = "Captured image"
        )
        Button(
            onClick = { remove(imageUri) }
        ) {
            Text("Remove image")
        }
    } else Row {
        Button(modifier = modifier.padding(Sizes.P), onClick = { gallery() }
        ) {
            Text("Galeria")
        }

        Button(modifier = modifier.padding(Sizes.P), onClick = { camera() }
        ) {
            Text("Camera")
        }

    }
}