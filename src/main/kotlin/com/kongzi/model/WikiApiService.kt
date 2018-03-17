package com.kongzi.model

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {

    /**
     * Equivalent to https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=Trump
     * Currently, [hitCountCheck] is not in use and there are no plans for usage.
     */
    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String = "query",
                      @Query("format") format: String = "json",
                      @Query("list") list: String = "search",
                      @Query("srsearch") srsearch: String):
            Observable<HitModel.Result>

    /**
     * Equivalent to https://en.wikipedia.org/w/api.php?action=query&format=json&list=backlinks&backlinkTitle=China&backlinkNamespace=0&backlinkFilterRedirects=nonredirects&backlinkLimit=50
     * Default settings exclude redirects and restrict the namespace to articles only.
     */
    @GET("api.php")
    fun backlinks(@Query("action") action: String = "query",
                  @Query("format") format: String = "json",
                  @Query("list") list: String = "backlinks",
                  @Query("bltitle") backlinkTitle: String,
                  @Query("blnamespace") backlinkNamespace: Int = 0,
                  @Query("blfilterredir") backlinkFilterRedirects: String = "nonredirects",
                  @Query("bllimit") backlinkLimit: Int = 50):
            Observable<BacklinkModel.Result>

    /**
     * Hardcoded to use en.wikipedia and SSL for now.
     */
    companion object {
        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://en.wikipedia.org/w/")
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

    /**
     * [HitModel] would be used to interpret results of [hitCountCheck]
     * Sample output:
     *
     * {
     *  batchcomplete: "",
     *   continue: {
     *    sroffset: 10,
     *    continue: "-||"
     *   },
     *   query: {
     *    searchinfo: {
     *     totalhits: 16776
     *   },
     *   search: [
     *   ...
     */
    object HitModel {
        data class Result(val query: Query)
        data class Query(val searchinfo: SearchInfo)
        data class SearchInfo(val totalhits: Int)
    }

    /**
     * The reason why [Backlink] is not defined here is mostly historical.
     * Sample output for [BacklinkModel]:
     *
     * {"batchcomplete":"",
     *   "continue": {
     *   "blcontinue":"0|2175",
     *  "continue":"-||"
     *  },
     *  "query": {
     *  "backlinks":[
     *  {"pageid":573,"ns":0,"title":"Alchemy"},
     *  {"pageid":597,"ns":0,"title":"Austroasiatic languages"},
     *  ...
     */
    object BacklinkModel {
        data class Result(val query: Query)
        data class Query(val backlinks: List<Backlink>)
        data class Backlink(val pageid: Int, val ns: Int, val title: String)
    }
}