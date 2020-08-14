package cvdevelopers.takehome

import android.app.Application
import cvdevelopers.takehome.dagger.ApplicationComponent
import cvdevelopers.takehome.dagger.ApplicationModule
import cvdevelopers.takehome.dagger.DaggerApplicationComponent

class LuminaryTakeHomeApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}