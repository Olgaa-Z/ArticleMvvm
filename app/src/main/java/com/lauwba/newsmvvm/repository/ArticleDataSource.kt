package com.lauwba.newsmvvm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.bumptech.glide.load.engine.Resource
import com.lauwba.newsmvvm.model.Article
import com.lauwba.newsmvvm.model.NewsResponse
import com.lauwba.newsmvvm.repository.service.RetrofitClient
import com.lauwba.newsmvvm.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class ArticleDataSource (val scope : CoroutineScope): PageKeyedDataSource<Int, Article>(){

//    for breakingnews
    val breakingNews : MutableLiveData<MutableList<Article>> =  MutableLiveData()
    var breakingPageNumber = 1
    var breakingNewsResponse : NewsResponse? = null

//    for searching news
    val searchNews : MutableLiveData<Resource<NewsResponse>> =  MutableLiveData()
    var searchingPageNumber = 1
    var searchingNewsResponse : NewsResponse? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        scope.launch{
            try {
                val response = RetrofitClient.api.getBreakingNews("in", 1, Constants.API_KEY)
                when {
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            breakingNews.postValue(it)
                            callback.onResult(it, null, 2)
                        }
                    }
                }
            }catch (exception : Exception){
                Log.e("DataSource : ", exception.message.toString())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        try{
            scope.launch {
                val response = RetrofitClient.api.getBreakingNews(
                    "in",
                    params.requestedLoadSize,
                    Constants.API_KEY
                )
                when {
                    response.isSuccessful -> {
                        response.body()?.articles?.let {

                            callback.onResult(it, params.key + 1)
                        }
                    }
                }
            }
        }catch (exception : Exception){
            Log.e("DataSource : ", exception.message.toString())
        }
    }

}