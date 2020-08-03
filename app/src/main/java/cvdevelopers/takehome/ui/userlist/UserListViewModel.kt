package cvdevelopers.takehome.ui.userlist

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.api.RandomUserRepository
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.database.UserDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val userDao: UserDao): ViewModel() {

    @Inject
    lateinit var randomUserRepository:RandomUserRepository
    private lateinit var subscription: Disposable
    val userListAdapter: UserListAdapter = UserListAdapter()
    val userData = MutableLiveData<List<Client>>()

    init {
        LuminaryTakeHomeApplication.appComponent.inject(this)
        this.randomUserRepository  = randomUserRepository
        loadData()
    }

    private fun loadData(){
        subscription = Observable.fromCallable { userDao.getAll() }
                .concatMap({ clientList ->
                    if(clientList.isEmpty()) {
                        randomUserRepository.loadUsers()?.concatMap {
                            remoteClientList ->
                            userData.postValue(remoteClientList)
                            userDao.insertUsers(remoteClientList)
                            Observable.just(remoteClientList)
                        }
                    } else
                        Observable.just(clientList)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSuccess ->
                            Log.d("UserListViewModel", "loading data...")
                            onRetrieveUserListSuccess(onSuccess as List<Client>)
                            userData.postValue(onSuccess)},
                        { onError ->
                            Log.d("UserListViewModel", "loading data...")
                            Log.d("UserListViewModel", onError.message)}
                )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        subscription.dispose()
    }

    private fun onRetrieveUserListSuccess(clientList:List<Client>){
        userListAdapter.updateUserList(clientList)
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
            loadData()
        }
    }
}