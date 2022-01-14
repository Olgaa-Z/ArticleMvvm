package com.lauwba.newsmvvm.repository

import com.lauwba.newsmvvm.model.Article
import com.lauwba.newsmvvm.repository.db.ArticleDatabase
import com.lauwba.newsmvvm.repository.service.RetrofitClient

class NewsRepository(val db : ArticleDatabase) {

    suspend fun getBreakingNews(countryCode : String, pageNumber : Int)=
        RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchBreaking(q : String, pageNumber : Int)=
        RetrofitClient.api.getSearchNews(q, pageNumber)

    suspend fun upsert(article : Article) = db.getArticleDao().insert(article)
    suspend fun delete(article : Article) = db.getArticleDao().deleteArticle(article)

}