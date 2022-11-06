package perm.amporosenok.debtorsappmvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import kotlinx.coroutines.launch
import perm.amporosenok.debtorsappmvvm.MainViewModel
import perm.amporosenok.debtorsappmvvm.MainViewModelFactory
import perm.amporosenok.debtorsappmvvm.model.Note
import perm.amporosenok.debtorsappmvvm.navigation.NavRoute
import perm.amporosenok.debtorsappmvvm.ui.theme.DebtorsAppMVVMTheme
import perm.amporosenok.debtorsappmvvm.utils.Constants
import perm.amporosenok.debtorsappmvvm.utils.Constants.Keys.MONEY
import perm.amporosenok.debtorsappmvvm.utils.Constants.Keys.NAME
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note =
        notes.firstOrNull { it.id == noteId?.toInt() } ?: Note(
            name = Constants.Keys.NONE,
            money = Constants.Keys.NONE,
            currentDate = Constants.Keys.NONE)

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var money by remember { mutableStateOf(Constants.Keys.EMPTY) }

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentD = sdf.format(Date())

    var currentDate by remember {
        mutableStateOf(currentD)
    }


    ModalBottomSheetLayout(sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface() {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = NAME) },
                        isError = name.isEmpty()
                    )
                    OutlinedTextField(
                        value = money,
                        onValueChange = { money = it },
                        label = { Text(text = MONEY) },
                        isError = money.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note =
                            Note(id = note.id,
                                name = name,
                                money = money,
                                currentDate = currentDate
                            )) {
                                navController.navigate(NavRoute.Main.route)
                            }
                        }) {
                        Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
            }
        }
    ) {

        Scaffold(modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(modifier = Modifier
                        .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.currentDate,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = note.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Text(
                                text = note.money,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }
                Row(modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            name = note.name
                            money = note.money
                            bottomSheetState.show()
                        }
                    }) {
                        Text(text = Constants.Keys.UPDATE)
                    }
                    Button(onClick = {
                        viewModel.deleteNote(note = note) {
                            navController.navigate(NavRoute.Main.route)
                        }
                    }) {
                        Text(text = Constants.Keys.DELETE)
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoute.Main.route)
                    }) {
                    Text(text = Constants.Keys.NAV_BACK)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevNoteScreen() {
    DebtorsAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}