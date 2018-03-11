package com.example

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {

    /* https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=Trump */
    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String = "query",
                      @Query("format") format: String = "json",
                      @Query("list") list: String = "search",
                      @Query("srsearch") srsearch: String):
            Observable<HitModel.Result>

    /* https://en.wikipedia.org/w/api.php?action=query&format=json&list=backlinks
       &bltitle=China&blnamespace=0&blfilterredir=nonredirects&bllimit=50 */
    @GET("api.php")
    fun backlinks(@Query("action") action: String = "query",
                  @Query("format") format: String = "json",
                  @Query("list") list: String = "backlinks",
                  @Query("bltitle") bltitle: String,
                  @Query("blnamespace") blnamespace: Int = 0, //Articles only
                  @Query("blfilterredir") blfilterredir: String = "nonredirects",
                  @Query("bllimit") bllimit: Int = 50):
            Observable<BacklinkModel.Result>

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

    object HitModel {
        data class Result(val query: Query)
        data class Query(val searchinfo: SearchInfo)
        data class SearchInfo(val totalhits: Int)
    }

/*
{
 batchcomplete: "",
 continue: {
  sroffset: 10,
  continue: "-||"
 },
 query: {
  searchinfo: {
   totalhits: 16776
 },
 search: [...
*/

    object BacklinkModel {
        data class Result(val query: Query)
        data class Query(val backlinks: List<Backlink>)
        data class Backlink(val pageid: Int, val ns: Int, val title: String)
    }

/*
{"batchcomplete":"",
 "continue": {
     "blcontinue":"0|2175",
     "continue":"-||"
     },
 "query": {
    "backlinks":[
       {"pageid":573,"ns":0,"title":"Alchemy"},
       {"pageid":597,"ns":0,"title":"Austroasiatic languages"},...

*/


}