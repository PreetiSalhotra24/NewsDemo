package web.connect.newsdemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import web.connect.newsdemo.data.models.NewsResponse
import web.connect.newsdemo.repository.AllNewsRepository
import web.connect.newsdemo.ui.constants.AppConstant.CATEGORY
import web.connect.newsdemo.ui.constants.AppConstant.COUNTRY

class AllNewsViewModel : ViewModel() {

    private val allNewsRepo by lazy {
        AllNewsRepository()
    }

    private val _topHeadlinesNews = MutableLiveData<NewsResponse>()
    val topHeadlinesNews: LiveData<NewsResponse> = _topHeadlinesNews

    fun fetchHeadlinesNews() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    allNewsRepo.getTopHeadlinesNews(
                        country = COUNTRY,
                        category = CATEGORY,
                    ).let {
                        _topHeadlinesNews.postValue(it)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteNews(news: NewsResponse.Article) {
        CoroutineScope(Dispatchers.IO).launch {
            val newList = _topHeadlinesNews.value?.articles?.toMutableList().also {
                it?.remove(news)
            }
            newList?.let {
                _topHeadlinesNews.postValue(_topHeadlinesNews.value?.copy(articles = newList))
            }
        }
    }

}