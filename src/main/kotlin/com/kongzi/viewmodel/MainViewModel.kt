package com.kongzi.viewmodel

import android.content.Context
import com.kongzi.model.Article
import com.kongzi.model.Backlink
import com.kongzi.model.IDataModel
import com.kongzi.model.ISchedulerProvider
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject

/**
 * View model for the main activity.
 */
public class MainViewModel {

    var mDataModel: IDataModel
    var mSelectedArticle: BehaviorSubject<Article> = BehaviorSubject.create()
    var mSchedulerProvider: ISchedulerProvider

    constructor(dataModel: IDataModel, schedulerProvider: ISchedulerProvider) {
        mDataModel = dataModel
        mSchedulerProvider = schedulerProvider
    }

    fun getBacklinks(): Observable<Backlink> {
        return mSelectedArticle.observeOn(mSchedulerProvider.computation())
                .map(Article::title)
                .flatMap(mDataModel::getBacklinks)
    }

    fun getCuoArticles(context: Context): Observable<List<Article>> {
        return mDataModel.getCuoArticles(context)
    }

    fun articleSelected(article: Article) {
        mSelectedArticle.onNext(article)
    }
}