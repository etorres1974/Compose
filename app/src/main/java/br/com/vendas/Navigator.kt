package br.com.vendas

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.vendas.ui.screens.Home
import br.com.vendas.ui.screens.NewProducts
import br.com.vendas.ui.screens.Settings

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    private val icon: ImageVector
) {
    object Home : Screen(
        "profile",
        R.string.nav_home,
        Icons.Filled.Home)

    object Settings : Screen(
        "settings",
        R.string.nav_settings,
        Icons.Filled.Settings
    )

    object NewProducts : Screen(
        "newProducts",
        R.string.nav_new_products,
        Icons.Filled.Favorite
    )

    @Composable
    fun Icon() = Icon(icon, contentDescription = null)

    @Composable
    fun Label() =  Text(stringResource(resourceId))
}

val items = listOf(
    Screen.Home,
    Screen.Settings,
    Screen.NewProducts,
)

@Composable
fun BottomNavBar() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { screen.Icon() },
                        label = { screen.Label() },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { Home(navController) }
            composable(Screen.Settings.route) { Settings(navController) }
            composable(Screen.NewProducts.route) { NewProducts(navController) }
        }
    }
}