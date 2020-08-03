package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.api.RandomUserRepository
import cvdevelopers.takehome.ui.MainActivity
import cvdevelopers.takehome.ui.base.ViewModelFactory
import cvdevelopers.takehome.ui.userlist.UserListFragment
import cvdevelopers.takehome.ui.userlist.UserListViewModel
import cvdevelopers.takehome.ui.userlist.UserViewModel
import cvdevelopers.takehome.utils.image.ImageLoader
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Created by CamiloVega on 10/7/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkClientModule::class))
interface ApplicationComponent {
    fun inject(app: LuminaryTakeHomeApplication)
    fun inject(target: MainActivity)
    fun inject(randomUserRepository: RandomUserRepository)
    fun inject(randomUserFragment: UserListFragment)
    fun inject(viewModelFactory: ViewModelFactory)
    fun inject(userListViewModel:UserListViewModel)
    fun inject(userViewModel: UserViewModel)
    fun inject(imageLoader: ImageLoader)

}