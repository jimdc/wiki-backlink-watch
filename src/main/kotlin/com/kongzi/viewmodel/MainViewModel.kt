package com.kongzi.viewmodel

import android.content.Context
import com.kongzi.model.Article
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import com.kongzi.model.IDataModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject

/**
 * View model for the main activity.
 */
public class MainViewModel (var mDataModel: IDataModel) {

    var mSelectedArticle: BehaviorSubject<Article> = BehaviorSubject.create()

    /**
     * Extract titles from articles, to give to [mDataModel.getBacklinks]
     * @return an observable of type [Backlink]
     */
    fun getBacklinks(): Observable<List<Backlink>> {
        return mSelectedArticle.observeOn(Schedulers.computation())
                .map(Article::title)
                .flatMap(mDataModel::getBacklinks)
    }

    fun getCuoArticles(context: Context): Observable<List<Article>> {
        return mDataModel.getCuoArticles(context)
    }

    /**
     * Called by the view to update [mSelectedArticle]
     */
    fun articleSelected(article: Article) {
        mSelectedArticle.onNext(article)
    }
}