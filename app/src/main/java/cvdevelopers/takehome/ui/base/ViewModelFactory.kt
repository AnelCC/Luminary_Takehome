package cvdevelopers.takehome.ui.base

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.room.Room
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.api.RandomUserRepository
import cvdevelopers.takehome.models.database.AppDatabase
import cvdevelopers.takehome.ui.userlist.UserListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory, LifecycleObserver{

    @Inject
    lateinit var randomUserRepository: RandomUserRepository


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            val userDatabase = AppDatabase.getDatabase(activity)
            // val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "users").build().userDao()
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(userDatabase.userDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}