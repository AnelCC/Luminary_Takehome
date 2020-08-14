package cvdevelopers.takehome.api

import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.models.Client
import io.reactivex.Observable
import javax.inject.Inject

class RandomUserRepository {

    @Inject
    lateinit var apiEndpoint: RandomUserApiEndpoint

    init {
        LuminaryTakeHomeApplication.appComponent.inject(this)
    }

    fun loadUsers(): Observable<List<Client>>? {
        var userListResponse = apiEndpoint.getClient("1").map { it.results}
        return userListResponse.toObservable();
    }
}