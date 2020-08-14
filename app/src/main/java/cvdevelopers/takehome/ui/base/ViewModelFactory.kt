package cvdevelopers.takehome.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import cvdevelopers.takehome.api.RandomUserRepository
import cvdevelopers.takehome.models.database.AppDatabase
import cvdevelopers.takehome.ui.userlist.UserListViewModel

import javax.inject.Inject

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory, LifecycleObserver{

    @Inject
    lateinit var randomUserRepository: RandomUserRepository


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            val userDatabase = AppDatabase.getDatabase(activity)
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(userDatabase.userDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}