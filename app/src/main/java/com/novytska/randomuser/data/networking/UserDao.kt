package com.novytska.randomuser.data.networking

import com.novytska.randomuser.BuildConfig
import com.novytska.randomuser.data.entity.CloudResponse
import com.novytska.randomuser.data.entity.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class UserDao {
    private val endpoint: UserEndpoint

    fun getAll(results: Int, page: Int): Observable<CloudResponse<User>> {
        return endpoint.getAll(
            results,
            page,
            DEFAULT_SEED
        )
    }

    private interface UserEndpoint {

        @GET("?")
        fun getAll(
            @Query(RESULTS) results: Int,
            @Query(PAGE) page: Int,
            @Query(SEED) seed: String
        ): Observable<CloudResponse<User>>

        companion object {
            const val PAGE = "page"
            const val RESULTS = "results"
            const val SEED = "seed"
        }
    }

    private object UserClient {
        private var retrofit: Retrofit? = null
        val client: Retrofit?
            get() {
                if (retrofit == null) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    val httpClient = OkHttpClient.Builder()
                    httpClient.addInterceptor(logging)
                    retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
                }
                return retrofit
            }
    }

    init {
        endpoint = UserClient.client!!.create(
            UserEndpoint::class.java)
    }

    companion object {
        const val DEFAULT_SEED = "abc"
    }
}