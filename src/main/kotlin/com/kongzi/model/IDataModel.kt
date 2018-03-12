package com.kongzi.model

import android.content.Context
import io.reactivex.Observable
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

interface IDataModel {
    fun getBacklinks(ArticleTitle: String): Observable<List<Backlink>>
    fun getCuoArticles(context: Context): Observable<List<Article>>
}