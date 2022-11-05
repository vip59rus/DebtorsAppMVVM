package perm.amporosenok.debtorsappmvvm.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import perm.amporosenok.debtorsappmvvm.MainViewModel
import perm.amporosenok.debtorsappmvvm.MainViewModelFactory
import perm.amporosenok.debtorsappmvvm.model.Note
import perm.amporosenok.debtorsappmvvm.navigation.NavRoute
import perm.amporosenok.debtorsappmvvm.ui.theme.DebtorsAppMVVMTheme

@Composable
fun MainScreen(navController: NavHostController) {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    val notes = mViewModel.readTest.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White)
            }
        }
    ) {
//        Column() {
//            NoteItem(currentDate = "22 10 01", name = "PETR", money = "100", navController = navController)
//            NoteItem(currentDate = "22 10 02", name = "PETR", money = "200", navController = navController)
//            NoteItem(currentDate = "22 10 03", name = "PETR", money = "300", navController = navController)
//            NoteItem(currentDate = "22 10 04", name = "PETR", money = "400", navController = navController)
//        }
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note, navController = navController)
            }
        }

    }
}


@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.currentDate,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = note.name)
                Text(text = note.money)
            }

        }
    }
}


@Preview
@Composable
fun PrevMainScreen() {
    DebtorsAppMVVMTheme() {
        MainScreen(navController = rememberNavController())
    }
}