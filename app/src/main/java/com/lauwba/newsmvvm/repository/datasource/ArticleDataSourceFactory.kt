package com.lauwba.newsmvvm.repository.datasource

import androidx.lifecycle.MutableLiveData
import com.lauwba.newsmvvm.model.Article
import com.lauwba.newsmvvm.repository.ArticleDataSource
import kotlinx.coroutines.CoroutineScope
import javax.sql.DataSource

class ArticleDataSourceFactory(private val scope : CoroutineScope) : androidx.paging.DataSource.Factory<Int, Article>() {

    val articleDataSourceLiveData =  MutableLiveData<ArticleDataSource>()


    override fun create(): androidx.paging.DataSource<Int, Article> {
        val newArticleDataSource = ArticleDataSource(scope)
        articleDataSourceLiveData.postValue(newArticleDataSource)
        return newArticleDataSource
    }


}