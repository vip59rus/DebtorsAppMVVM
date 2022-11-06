package perm.amporosenok.debtorsappmvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import perm.amporosenok.debtorsappmvvm.MainViewModel
import perm.amporosenok.debtorsappmvvm.MainViewModelFactory
import perm.amporosenok.debtorsappmvvm.navigation.NavRoute
import perm.amporosenok.debtorsappmvvm.ui.theme.DebtorsAppMVVMTheme
import perm.amporosenok.debtorsappmvvm.utils.TYPE_FIREBASE
import perm.amporosenok.debtorsappmvvm.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {


    val context = LocalContext.current
    val mViewModel:MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    //hohoho

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will we use?")
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM){
                        navController.navigate(route = NavRoute.Main.route)
                    }

                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Room Database")
            }

            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE){
                        navController.navigate(route = NavRoute.Main.route)
                    }


                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Firebase Database")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Column(modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Вам Должны")
                    Text(text = "1000")
                }
                Column(modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Вы Должны")
                    Text(text = "300")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {
    DebtorsAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}