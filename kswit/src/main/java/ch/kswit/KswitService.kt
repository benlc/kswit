package ch.kswit

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KswitService {

    @GET("ks/{package-name}")
    fun getAppState(@Path("package-name") packageName: String): Single<Response<Void>>
}