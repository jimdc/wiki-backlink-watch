package com.kongzi.model

import io.reactivex.Observable

interface IDataModel {
    fun getBacklinks(ArticleTitle: String): Observable<Backlink>
    fun getCuoArticles(): Observable<List<Article>>
}