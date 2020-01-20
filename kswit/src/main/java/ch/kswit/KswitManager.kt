package ch.kswit

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class KswitManager(val context: Context) {

    private val disposables = CompositeDisposable()
    private val service: KswitService

    init { service = createWebService(createOkHttpClient(), "https://firestore.googleapis.com/v1/projects/benlc-5345d/databases/(default)/documents/ks") }

    fun start() {
        disposables.add(service.getAppState(context.packageName).retry(3).subscribe(
            {},
            {}
        ))
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun createWebService(okHttpClient: OkHttpClient, baseUrl: String): KswitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(KswitService::class.java)
    }
}