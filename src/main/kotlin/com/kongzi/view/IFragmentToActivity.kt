package com.kongzi.view
import com.kongzi.model.Article

public interface IFragmentToActivity {
    fun selectArticle (article: Article)
    fun whichArticleInFocus(): Article
}