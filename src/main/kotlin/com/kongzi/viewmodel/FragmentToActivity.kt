package com.kongzi.viewmodel
import com.kongzi.model.Article
import io.reactivex.disposables.CompositeDisposable

interface FragmentToActivity {
    fun selectArticle (article: Article)
    fun whichArticleInFocus(): Article
    fun getCompositeDisposable(): CompositeDisposable
}