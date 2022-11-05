package perm.amporosenok.debtorsappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import perm.amporosenok.debtorsappmvvm.database.room.dao.AppRoomDatabase
import perm.amporosenok.debtorsappmvvm.database.room.dao.repository.RoomRepository
import perm.amporosenok.debtorsappmvvm.model.Note
import perm.amporosenok.debtorsappmvvm.utils.REPOSITORY
import perm.amporosenok.debtorsappmvvm.utils.TYPE_FIREBASE
import perm.amporosenok.debtorsappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData", "MainViewModel initDataBase with type: $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
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