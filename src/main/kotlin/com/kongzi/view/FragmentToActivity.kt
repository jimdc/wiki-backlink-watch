package com.kongzi.view
import com.kongzi.model.Article

public interface FragmentToActivity {
    fun selectArticle (article: Article)
    fun whichArticleInFocus(): Article
}