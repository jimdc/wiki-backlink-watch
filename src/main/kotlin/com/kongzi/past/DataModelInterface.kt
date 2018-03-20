package com.kongzi.past

import android.content.Context
import io.reactivex.Observable
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

interface DataModelInterface {
    fun getBacklinks(articleTitle: String): Observable<List<Backlink>>
    fun getCuoArticles(context: Context): Observable<List<Article>>
}