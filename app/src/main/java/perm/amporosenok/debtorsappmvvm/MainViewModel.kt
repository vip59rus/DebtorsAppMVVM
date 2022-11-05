package perm.amporosenok.debtorsappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import perm.amporosenok.debtorsappmvvm.model.Note
import perm.amporosenok.debtorsappmvvm.utils.TYPE_FIREBASE
import perm.amporosenok.debtorsappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when (dbType.value) {
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(currentDate = "01-08-21", name = "Vasya", money = "100"),
                        Note(currentDate = "02-08-21", name = "Petya", money = "200"),
                        Note(currentDate = "03-08-21", name = "Grisha", money = "300"),
                        Note(currentDate = "04-08-21", name = "Masha", money = "400"),
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()

            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel initDataBase with type: $type")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}