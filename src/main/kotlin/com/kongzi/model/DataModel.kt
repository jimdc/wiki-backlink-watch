package com.kongzi.model

/**
 * Created by james on 11/03/2018.
 */
import android.content.Context
import android.util.Log
import com.kongzi.R
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import io.reactivex.Observable


class DataModel : DataModelInterface {

    private val wikiApiServe = WikiApiService.create()

    /**
     * Backlinks for an article are subject to change.
     * @param[ArticleTitle] because searches are done this way in [WikiApiService]
     */
    override fun getBacklinks(ArticleTitle: String): Observable<List<Backlink>> {
        return wikiApiServe.backlinks(bltitle = ArticleTitle).map{ it -> it.query.backlinks }
    }

    /**
     * @return Realistic dummy backlinks corresponding to default articles
     */
    private fun defaultBacklinks(ArticleTitle: String): Observable<List<Backlink>> {
        when(ArticleTitle) {
            "Banana Fish" -> {
                return Observable.just(listOf(
                        Backlink(1048552, 0, "Animerica Extra"),
                        Backlink(54192849, 0, "List of Saekano: How to Raise a Boring Girlfriend episodes"),
                        Backlink(2535955, 0, "GeGeGe no Kitarō"),
                        Backlink(55057023, 0, "Wotakoi: Love is Hard for Otaku")
                ))
            }
            "Sigismund Rákóczi" -> {
                return Observable.just(listOf(
                        Backlink(1231232, 0,"St. Michael's Church, Cluj-Napoca"),
                        Backlink(4745801, 0, "Early Modern Romania"),
                        Backlink(31377556, 0, "Michael Weiß (politician)"),
                        Backlink(38935275, 0, "List of Princes of Transylvania")
                ))
            }
            "Li (surname 李)" -> {
                return Observable.just(listOf(
                        Backlink(10814, 0, "Surnames by country"),
                        Backlink(35068725, 0, "Lý (Vietnamese surname)"),
                        Backlink(44291458, 0, "Lan (surname 蓝)"),
                        Backlink(51898759, 0, "ABC Chinese–English Dictionary")
                ))
            }
        }

        return Observable.empty()
    }

    /**
     * Load article list from saved preferences
     */
    override fun getCuoArticles(context: Context): Observable<List<Article>> {

        val titles = context.resources.getStringArray(R.array.articlelist_array)
        val pageids = context.resources.getStringArray(R.array.articlelist_array_values)
        val articlePreflist = mutableListOf<Article>()

        try {
            pageids.forEachIndexed { index, s -> articlePreflist.add(Article(s.toInt(), titles[index])) }
        } catch (nfe: NumberFormatException) {
            Log.d("DataModel", "I could not parse one of the loaded pageids into an integer.")
            nfe.printStackTrace()
            return Observable.empty()
        }

        return Observable.just(articlePreflist)
    }
}