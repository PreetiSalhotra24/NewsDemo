package web.connect.newsdemo.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import web.connect.newsdemo.BuildConfig
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var retrofitBuild: Retrofit

    fun getApiServices(): ApiServices {
        if (!this::retrofitBuild.isInitialized) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build()

            retrofitBuild = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitBuild.create(ApiServices::class.java)
    }

}