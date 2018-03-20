package com.kongzi.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.kongzi.BR

data class Baqlink(
        private val _pageid: Int,
        private var _ns: Int,
        private var _title: String) : BaseObservable() {

    val pageid: Int
        get() = _pageid

    var ns: Int
    @Bindable get() = _ns
    set(value) {
        _ns = value
        notifyPropertyChanged(BR.ns)
    }

    var title: String
    @Bindable get() = _title
    set(value) {
        _title = value
        notifyPropertyChanged(BR.title)
    }
}