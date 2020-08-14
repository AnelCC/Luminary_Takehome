package cvdevelopers.takehome.dagger

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.api.RandomUserRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkClientModule  {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideRandomUserEndpoint(gson: Gson): RandomUserApiEndpoint = Retrofit.Builder()
            .baseUrl(RandomUserApiEndpoint.SERVER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RandomUserApiEndpoint::class.java)

    @Provides
    @Singleton
    fun provideRepository (): RandomUserRepository {
        return RandomUserRepository()
    }
}
