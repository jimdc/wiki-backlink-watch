package com.kongzi.viewmodel

import com.kongzi.model.Backlink
import com.kongzi.model.IBacklinkModel
import io.reactivex.Observable

class BacklinkViewModel(private var mBacklinkModel: IBacklinkModel) {

    fun getBacklinks(): Observable<Backlink> { return mBacklinkModel.blinkStream }

/*
    private var blinkStream: Backlink
    private val context: Context

    constructor (blinkStream: Backlink, context: Context) {
        this.blinkStream = blinkStream
        this.context = context
    }

    val pageid: Int
        @Bindable get() = blinkStream.pageid

    val ns: Int
        @Bindable get() = blinkStream.ns

    val title: String
        @Bindable get() = blinkStream.title

    fun setBacklink(blinkStream: Backlink) {
        this.blinkStream = blinkStream
        notifyChange()
    }

    fun getSomeData(): Observable<List<Backlink>> {
        return mDataModel.getSomeData()
    }
    */
}