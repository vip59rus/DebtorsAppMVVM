package perm.amporosenok.debtorsappmvvm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import perm.amporosenok.debtorsappmvvm.navigation.NavRoute
import perm.amporosenok.debtorsappmvvm.ui.theme.DebtorsAppMVVMTheme

@Composable
fun AddScreen(navController: NavHostController) {

    var currentDate by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }
    var money by remember {
        mutableStateOf("")
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
                onValueChange = { name = it },
                label = { Text(text = "Note Name") }
            )

            OutlinedTextField(
                value = money,
                onValueChange = { money = it },
                label = { Text(text = "Note Money") }
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.Main.route)
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
        AddScreen(navController = rememberNavController())
    }
}