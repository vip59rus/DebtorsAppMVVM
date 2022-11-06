package perm.amporosenok.debtorsappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import perm.amporosenok.debtorsappmvvm.MainViewModel
import perm.amporosenok.debtorsappmvvm.screens.*
import perm.amporosenok.debtorsappmvvm.utils.Constants
import perm.amporosenok.debtorsappmvvm.utils.Constants.Screens.ADD_SCREEN
import perm.amporosenok.debtorsappmvvm.utils.Constants.Screens.MAIN_SCREEN
import perm.amporosenok.debtorsappmvvm.utils.Constants.Screens.NOTE_SCREEN
import perm.amporosenok.debtorsappmvvm.utils.Constants.Screens.START_SCREEN


sealed class NavRoute(val route: String) {
    object Start : NavRoute(START_SCREEN)
    object Main : NavRoute(MAIN_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Note : NavRoute(NOTE_SCREEN)
}

@Composable
fun DebtorsNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) {
            StartScreen(navController = navController,
                viewModel = mViewModel)
        }
        composable(NavRoute.Main.route) {
            MainScreen(navController = navController,
                viewModel = mViewModel)
        }
        composable(NavRoute.Add.route) {
            AddScreen(navController = navController,
                viewModel = mViewModel)
        }
        composable(NavRoute.Note.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            NoteScreen(navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.ID))
        }
    }
}