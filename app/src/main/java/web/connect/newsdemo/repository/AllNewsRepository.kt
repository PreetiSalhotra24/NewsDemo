package web.connect.newsdemo.repository

import web.connect.newsdemo.data.models.NewsResponse
import web.connect.newsdemo.data.network.ApiClient

class AllNewsRepository {

    private val apiServices by lazy {
        ApiClient.getApiServices()
    }

    suspend fun getTopHeadlinesNews(country: String, category: String): NewsResponse {
        return apiServices.getTopHeadlinesNews(
            country = country,
            category = category,
        )
    }

}