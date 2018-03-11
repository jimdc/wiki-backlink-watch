package com.kongzi.model

/**
 * Created by james on 11/03/2018.
 */
import com.kongzi.wikiApiServe
import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import java.util.*


class DataModel : IDataModel {

    /**
     * @todo: Replace with wikiApiServe.backlinks(bltitle = ArticleTitle)
     */
    override fun getBacklinks(ArticleTitle: String): Observable<Backlink> {
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

    override fun getCuoArticles(): Observable<List<Article>> {
        return Observable.fromCallable(this::getArticles)
    }

    /**
     * @todo: Replace with Settings
     */
    private fun getArticles(): List<Article> {
        return Arrays.asList(
                Article(1028119, "Banana Fish"),
                Article(3276701, "Sigismund Rákóczi"),
                Article(41579001, "Li (surname 李)")
        )
    }
}