package perm.amporosenok.debtorsappmvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {

    var currentDate by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }
    var money by remember {
        mutableStateOf("")
    }

    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

    Scaffold() {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add New Debtor",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Current Date",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isButtonEnabled = name.isNotEmpty() && money.isNotEmpty()
                },
                label = { Text(text = "Note Name") },
                isError = name.isEmpty()
            )

            OutlinedTextField(
                value = money,
                onValueChange = {
                    money = it
                    isButtonEnabled = name.isNotEmpty() && money.isNotEmpty()
                },
                label = { Text(text = "Note Money") },
                isError = money.isEmpty()
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(
                        note = Note(
                            currentDate = currentDate,
                            name = name,
                            money = money,
                        )) {
                        navController.navigate(NavRoute.Main.route)
                    }

                }
            ) {
                Text(text = "Add Debtor")
            }
        }

    }
}


@Preview
@Composable
fun PrevAddScreen() {
    DebtorsAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}