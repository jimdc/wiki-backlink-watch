package com.kongzi.model

/**
 * Created by james on 11/03/2018.
 */
import android.content.Context
import android.support.v7.preference.PreferenceManager
import android.util.ArraySet
import com.kongzi.R
import com.kongzi.wikiApiServe
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableCache.from
import org.intellij.lang.annotations.Language
import java.util.*


class DataModel : IDataModel {

    override fun getBacklinks(ArticleTitle: String): Observable<Backlink> {

        // Returning 0, null for some reason.
        // return wikiApiServe.backlinks(bltitle = ArticleTitle).flatMapIterable{ result -> result.query.backlinks }

        return defaultBacklinks(ArticleTitle)
    }

    private fun defaultBacklinks(ArticleTitle: String): Observable<Backlink> {
        when(ArticleTitle) {
            "Banana Fish" -> { return Observable.just(Backlink
            (1048552, 0, "Animerica Extra")) }
            "Sigismund Rákóczi" -> { return Observable.just(Backlink
            (1231232, 0,"St. Michael's Church, Cluj-Napoca")) }
            "Li (surname 李)" -> { return Observable.just(Backlink
            (10814, 0, "Surnames by country")) }
        }

        return Observable.just(Backlink(
                24588706, 0, "Hunor Kelemen")) //or maybe just Observable.empty()
    }

    override fun getCuoArticles(context: Context): Observable<List<Article>> {

        val titles = context.resources.getStringArray(R.array.articlelist_array)
        val pageids = context.resources.getStringArray(R.array.articlelist_array_values)
        val articlePreflist = mutableListOf<Article>()
        pageids.forEachIndexed { index, s -> articlePreflist.add(Article(s.toInt(), titles[index])) }

        return Observable.just(articlePreflist)
    }
}