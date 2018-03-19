package com.kongzi.viewmodel

import android.content.Context
import com.kongzi.model.Article
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import com.kongzi.model.DataModelInterface
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * View model for the main activity.
 * @todo: extend AndroidViewModel instead of passing context as parameter
 */
class MainViewModel (private var dataModel: DataModelInterface) {

    var selectedArticle: BehaviorSubject<Article> = BehaviorSubject.create()

    /**
     * Extract titles from articles, to give to [dataModel.getBacklinks]
     * @return an observable of type [Backlink]
     */
    fun getBacklinks(): Observable<List<Backlink>> {
        return selectedArticle.observeOn(Schedulers.computation())
                .map(Article::title)
                .flatMap(dataModel::getBacklinks)
    }

    fun getCuoArticles(context: Context): Observable<List<Article>> {
        return dataModel.getCuoArticles(context)
    }

    /**
     * Called by the view to update [selectedArticle]
     */
    fun articleSelected(article: Article) {
        selectedArticle.onNext(article)
    }
}