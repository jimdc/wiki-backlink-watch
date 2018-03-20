package com.kongzi.past
import com.kongzi.past.Article
import io.reactivex.disposables.CompositeDisposable

interface FragmentToActivity {
    fun selectArticle (article: Article)
    fun whichArticleInFocus(): Article
    fun getCompositeDisposable(): CompositeDisposable
}