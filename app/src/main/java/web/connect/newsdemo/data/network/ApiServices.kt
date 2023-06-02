package web.connect.newsdemo.data.network

import retrofit2.http.*
import web.connect.newsdemo.BuildConfig
import web.connect.newsdemo.data.models.NewsResponse

interface ApiServices {

    // All News
    @GET(ApiEndPoints.GET_TOP_HEADLINES)
    suspend fun getTopHeadlinesNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.Api_Key
    ):NewsResponse

}