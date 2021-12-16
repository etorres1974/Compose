package br.com.vendas.ui.utils.camera

import android.Manifest
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import br.com.vendas.ui.utils.Permission
import br.com.vendas.ui.utils.permissionNotAvaiable

@Composable
fun CameraSelect(modifier: Modifier = Modifier, onImageFile: (Uri) -> Unit) {
    CameraCapture(
        modifier = modifier,
        onImageFile = { file ->
            onImageFile(file.toUri())
        }
    )
}
@Composable
fun CameraMode(modifier: Modifier = Modifier, onImageFile: (Uri) -> Unit){
    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "Permissão da Camera é necessária para adicionar novos produtos.",
        permissionNotAvailableContent = {
            permissionNotAvaiable(
                modifier = modifier,
                context = context
            )
        }
    ) {
        CameraMode(modifier = modifier, onImageFile = onImageFile)
    }
}