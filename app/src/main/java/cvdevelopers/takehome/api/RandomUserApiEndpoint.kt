package cvdevelopers.takehome.api

import cvdevelopers.takehome.models.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiEndpoint {

  @GET("/api/")
  fun getClient(@Query("page") page: String, @Query("results") results: String = "30"): Single<ApiResponse>

  companion object {
    val SERVER = "https://randomuser.me"
  }
}