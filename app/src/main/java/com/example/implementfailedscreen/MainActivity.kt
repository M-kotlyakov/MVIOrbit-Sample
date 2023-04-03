package com.example.implementfailedscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.implementfailedscreen.ui.screens.BottomBarScreen
import com.example.implementfailedscreen.ui.screens.DefaultNavigationScreens
import com.example.implementfailedscreen.ui.screens.items
import com.example.implementfailedscreen.ui.ServerPage
import com.example.implementfailedscreen.ui.failed.FailedPage
import com.example.implementfailedscreen.ui.first.FirstPage
import com.example.implementfailedscreen.ui.first.FirstSideEffect
import com.example.implementfailedscreen.ui.first.FirstViewModel
import com.example.implementfailedscreen.ui.second.SecondPage
import com.example.implementfailedscreen.ui.theme.ImplementFailedScreenTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImplementFailedScreenTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null)},
                        label = { Text(text = stringResource(id = screen.resourceId))},
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.First.route,
            Modifier.padding(innerPadding)
        ) {
            addFirst(navController = navController)
            addSecond(navController = navController)
            addFailed(navController = navController)
            addServer(navController = navController)
        }
    }
}

private fun NavGraphBuilder.addFirst(navController: NavController) {
    composable(BottomBarScreen.First.route) {
        val vm = FirstViewModel()
        val state by vm.collectAsState()
        vm.collectSideEffect {
            when(it) {
                is FirstSideEffect.ShowServerMessage -> {
                    navController.navigate(route = DefaultNavigationScreens.Server.route)
                }
            }
        }
        FirstPage(
            state = state,
            navController = navController,
            showText = {text -> vm.addServerMessage(text)}
        )
    }
}

private fun NavGraphBuilder.addSecond(navController: NavController) {
    composable(BottomBarScreen.Second.route) {
        SecondPage(navController = navController)
    }
}

private fun NavGraphBuilder.addFailed(navController: NavController) {
    composable(
        route = "${DefaultNavigationScreens.Failed.route}/{message}",
        arguments = listOf(navArgument("message") { type = NavType.StringType })
    ) { backStackEntry ->

        FailedPage(
            navController = navController,
            errorMessage = backStackEntry.arguments?.getString("message")
        )
    }
}

private fun NavGraphBuilder.addServer(navController: NavController) {
    composable( route = DefaultNavigationScreens.Server.route) {

        ServerPage(navController = navController)
    }
}