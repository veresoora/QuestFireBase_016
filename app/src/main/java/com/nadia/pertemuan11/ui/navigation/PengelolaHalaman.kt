package com.nadia.pertemuan11.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nadia.pertemuan11.ui.home.pages.HomeScreen
import com.nadia.pertemuan11.ui.home.pages.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToltemEntry = {
                    navController.navigate(DestinasiInsert.route)
                }
            )
        }

        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.navigate(DestinasiHome.route) }
            )
        }
    }
}