package br.com.vendas.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermission(
    permissions: List<String> = listOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA,
    ),
    content: @Composable () -> Unit = { }
) {
    val context = LocalContext.current
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    MultiplePermissionsFlow(
        multiplePermissionsState,
        navigateToSettingsScreen = { navigateToSettingsScreen(context) },
        content = content
    )
}

@ExperimentalPermissionsApi
@Composable
private fun PermissionDenied(
    multiplePermissionsState : MultiplePermissionsState,
    disableRationale : (Boolean) -> Unit,
    rationale: String
)  =
Column {
    val revokedPermissionsText = getPermissionsText(
        multiplePermissionsState.revokedPermissions
    )
    Text(
        "$revokedPermissionsText important.\n$rationale"
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row {
        Button(
            onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }
        ) {
            Text("Request permissions")
        }
        Spacer(Modifier.width(8.dp))
        Button(onClick = { disableRationale(true) }) {
            Text("Don't show rationale again")
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MultiplePermissionsFlow(
    multiplePermissionsState: MultiplePermissionsState,
    navigateToSettingsScreen: () -> Unit,
    rationale: String = "This permission is important for this app. Please grant the permission.",
    content: @Composable () -> Unit = { Text("Camera and Read storage permissions Granted! Thank you!") },
    contentDisable: @Composable () -> Unit = { Text("Feature not available") }
) {
    // Track if the user doesn't want to see the rationale any more.
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    when {
        // If all permissions are granted, then show screen with the feature enabled
        multiplePermissionsState.allPermissionsGranted -> { content() }
        // If the user denied any permission but a rationale should be shown, or the user sees
        // the permissions for the first time, explain why the feature is needed by the app and
        // allow the user decide if they don't want to see the rationale any more.
        multiplePermissionsState.shouldShowRationale || !multiplePermissionsState.permissionRequested -> {
            if (doNotShowRationale) {
                contentDisable()
            } else {
                PermissionDenied(multiplePermissionsState = multiplePermissionsState, {doNotShowRationale = true}, rationale)
            }
        }
        // If the criteria above hasn't been met, the user denied some permission. Let's present
        // the user with a FAQ in case they want to know more and send them to the Settings screen
        // to enable them the future there if they want to.
        else -> {
            Column {
                val revokedPermissionsText = getPermissionsText(
                    multiplePermissionsState.revokedPermissions
                )
                Text(
                    "$revokedPermissionsText denied. See this FAQ with " +
                            "information about why we need this permission. Please, grant us " +
                            "access on the Settings screen."
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = navigateToSettingsScreen) {
                    Text("Open Settings")
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun getPermissionsText(permissions: List<PermissionState>): String {
    val revokedPermissionsSize = permissions.size
    if (revokedPermissionsSize == 0) return ""

    val textToShow = StringBuilder().apply {
        append("The ")
    }

    for (i in permissions.indices) {
        textToShow.append(permissions[i].permission)
        when {
            revokedPermissionsSize > 1 && i == revokedPermissionsSize - 2 -> {
                textToShow.append(", and ")
            }
            i == revokedPermissionsSize - 1 -> {
                textToShow.append(" ")
            }
            else -> {
                textToShow.append(", ")
            }
        }
    }
    textToShow.append(if (revokedPermissionsSize == 1) "permission is" else "permissions are")
    return textToShow.toString()
}