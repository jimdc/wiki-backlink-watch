package com.kongzi.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.support.v7.widget.LinearLayoutManager
import com.kongzi.R
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import com.kongzi.wikiApiServe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

public class RecyclerViewFragment : Fragment() {
    var mDataset: MutableList<Backlink> = ArrayList()
    protected lateinit var mRecyclerView: RecyclerView
    public lateinit var mAdapter: BacklinkAdapter
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataset()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater?.inflate(R.layout.recycler_view_frag, container, false)
        mAdapter = BacklinkAdapter(mDataset)
        mRecyclerView = rootView!!.findViewById(R.id.recyclerView)
        mRecyclerView.setAdapter(mAdapter)

        mRecyclerView.setLayoutManager(LinearLayoutManager(activity))
        mRecyclerView.scrollToPosition(0)

        return rootView
    }

    private fun initDataset() {
        disposable = wikiApiServe.backlinks(bltitle = "Donald Trump")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            result.query.backlinks.forEach {
                                mDataset.add(it)
                            }
                        },
                        { error -> Log.d("RecyclerViewFragment", error.toString()) }
                )
    }
}