package com.example.employeedetails.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.employeedetails.ui.screens.EmployeeDetailsRoute
import com.example.employeedetails.ui.screens.EmployeeListScreenRoute
import com.example.employeedetails.util.AppConstant

sealed class Route(val name: String) {
    data object EmployeeListScreen : Route("EmployeeList")

    data object EmployeeDetailsScreen : Route("EmployeeDetails/")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.EmployeeListScreen.name
    ) {
        composable(route = Route.EmployeeListScreen.name) {
            EmployeeListScreenRoute(onNewsClick = {
                navController.navigate(Route.EmployeeDetailsScreen.name + it)
            })
        }
        composable(route = Route.EmployeeDetailsScreen.name + AppConstant.EMP_ID,
            arguments = listOf(
                navArgument(AppConstant.EMP_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            EmployeeDetailsRoute(it.arguments!!.getInt(AppConstant.EMP_ID))
        }
    }
}