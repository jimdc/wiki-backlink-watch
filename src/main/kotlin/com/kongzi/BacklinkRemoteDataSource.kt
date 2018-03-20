package com.kongzi

import com.kongzi.model.Baqlink
import com.kongzi.model.WikiApiService
import io.reactivex.Observable


private const val ARTICLE_TITLE = "Donald_Trump"

class BacklinkRemoteDataSource {

    private val wikiApiServe = WikiApiService.create()

    fun getBacklinks() : Observable<List<Baqlink>> {

        val backlinks = wikiApiServe.backlinks(backlinkTitle = ARTICLE_TITLE)
                .map { it -> it.query.backlinks }

        val baqlinks = backlinks.flatMap { list -> Observable.fromIterable(list)
                                            .map { item -> Baqlink(item.pageid, item.ns, item.title) }
                                                .toList()
                                                .toObservable()
        }

        return Observable.just(listOf(
                Baqlink(1, 0, "Hello"),
                Baqlink(2, 0, "Goodbye")
        ))

        return baqlinks
    }
}