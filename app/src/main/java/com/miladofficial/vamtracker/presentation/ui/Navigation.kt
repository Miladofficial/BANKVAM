package com.miladofficial.vamtracker.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.miladofficial.vamtracker.presentation.ui.screens.*

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object VamList : Screen("vam_list")
    object AddEditVam : Screen("add_edit_vam/{vamId}") {
        fun passId(vamId: Long = 0) = "add_edit_vam/$vamId"
    }
    object VamDetails : Screen("vam_details/{vamId}") {
        fun passId(vamId: Long) = "vam_details/$vamId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }

        composable(Screen.VamList.route) {
            VamListScreen(
                onAddClick = { navController.navigate(Screen.AddEditVam.passId()) },
                onItemClick = { vamId -> navController.navigate(Screen.VamDetails.passId(vamId)) }
            )
        }

        composable(
            route = Screen.AddEditVam.route,
            arguments = listOf(navArgument("vamId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vamId = backStackEntry.arguments?.getLong("vamId") ?: 0
            AddEditVamScreen(
                vamId = vamId,
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.VamDetails.route,
            arguments = listOf(navArgument("vamId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vamId = backStackEntry.arguments?.getLong("vamId") ?: 0
            VamDetailsScreen(
                vamId = vamId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}