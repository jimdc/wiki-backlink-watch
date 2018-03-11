package com.kongzi.model

import android.content.Context
import io.reactivex.Observable

interface IDataModel {
    fun getBacklinks(ArticleTitle: String): Observable<List<Backlink>>
    fun getCuoArticles(context: Context): Observable<List<Article>>
}