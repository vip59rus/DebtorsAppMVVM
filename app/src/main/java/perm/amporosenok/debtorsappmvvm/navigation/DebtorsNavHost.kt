package perm.amporosenok.debtorsappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import perm.amporosenok.debtorsappmvvm.MainViewModel
import perm.amporosenok.debtorsappmvvm.screens.*


sealed class NavRoute(val route:String){
    object Start:NavRoute("start_screen")
    object Main:NavRoute("main_screen")
    object Add:NavRoute("add_screen")
    object Note:NavRoute("note_screen")
}

@Composable
fun DebtorsNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route ){
        composable(NavRoute.Start.route){ StartScreen(navController = navController, viewModel=mViewModel) }
        composable(NavRoute.Main.route){ MainScreen(navController = navController, viewModel=mViewModel) }
        composable(NavRoute.Add.route){ AddScreen(navController = navController, viewModel=mViewModel) }
        composable(NavRoute.Note.route){ NoteScreen(navController = navController, viewModel=mViewModel) }
    }
}