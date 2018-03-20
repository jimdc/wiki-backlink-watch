package com.kongzi

import com.kongzi.model.Baqlink
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class BacklinkLocalDataSource {

    fun getBacklinks() : Observable<List<Baqlink>> {
        return defaultBacklinks("Banana_Fish")
    }

    fun saveBacklinks(list: List<Baqlink>): Completable {
        //todo save repositories in DB
        return Single.just(1).toCompletable()
        //return Single.just(1).delay(1, TimeUnit.SECONDS).toCompletable()
    }

    private fun defaultBacklinks(ArticleTitle: String): Observable<List<Baqlink>> {
        when(ArticleTitle) {
            "Banana Fish" -> {
                return Observable.just(listOf(
                        Baqlink(1048552, 0, "Animerica Extra"),
                        Baqlink(54192849, 0, "List of Saekano: How to Raise a Boring Girlfriend episodes"),
                        Baqlink(2535955, 0, "GeGeGe no Kitarō"),
                        Baqlink(55057023, 0, "Wotakoi: Love is Hard for Otaku")
                ))
            }
            "Sigismund Rákóczi" -> {
                return Observable.just(arrayListOf(
                        Baqlink(1231232, 0, "St. Michael's Church, Cluj-Napoca"),
                        Baqlink(4745801, 0, "Early Modern Romania"),
                        Baqlink(31377556, 0, "Michael Weiß (politician)"),
                        Baqlink(38935275, 0, "List of Princes of Transylvania")
                ))
            }
            "Li (surname 李)" -> {
                return Observable.just(arrayListOf(
                        Baqlink(10814, 0, "Surnames by country"),
                        Baqlink(35068725, 0, "Lý (Vietnamese surname)"),
                        Baqlink(44291458, 0, "Lan (surname 蓝)"),
                        Baqlink(51898759, 0, "ABC Chinese–English Dictionary")
                ))
            }
        }

        return Observable.empty()
    }
}