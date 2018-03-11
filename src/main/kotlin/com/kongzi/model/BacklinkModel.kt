package com.kongzi.model

/**
 * Created by james on 11/03/2018.
 */
import android.util.Log
import com.kongzi.wikiApiServe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable

class BacklinkModel : IBacklinkModel {
    override val blinkStream: Observable<Backlink>
        get() =
            //Observable.just(Backlink(1234, 0, "Privyet"))
            wikiApiServe.backlinks(bltitle = "Donald Trump")
                    .map{it -> it.query.backlinks} //Transform from Observable<Result> to Observable<List<Backlink>>
                    .flatMapIterable{ collection -> collection } //From Observable<List<T>> to Observable<T>
                    /*
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                result.query.backlinks.forEach {
                                    //mDataset.add(it)
                                }
                            },
                            { error -> Log.d("RecyclerViewFragment", error.toString()) })*/

}