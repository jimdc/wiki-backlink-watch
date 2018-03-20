package com.kongzi.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.kongzi.BacklinkRepository
import com.kongzi.model.Baqlink
import com.kongzi.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(var backlinkRepository: BacklinkRepository)
    : ViewModel() {

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)


    var repositories = MutableLiveData<List<Baqlink>>()
    private val compositeDisposable = CompositeDisposable()

    fun loadRepositories() {
        isLoading.set(true)

        compositeDisposable += backlinkRepository
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<List<Baqlink>>() {

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError called with ${e.localizedMessage}")
            }

            override fun onNext(data: List<Baqlink>) {
                Log.d(TAG, "onNext called with ${data.size} list array")
                repositories.value = data
            }

            override fun onComplete() {
                isLoading.set(false)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }


}