package com.kongzi.model

import io.reactivex.Observable

interface IBacklinkModel {
    val blinkStream: Observable<Backlink>
}