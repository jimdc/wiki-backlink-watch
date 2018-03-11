package com.kongzi.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import com.kongzi.R
import android.arch.lifecycle.ViewModelProviders
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

public class RecyclerViewFragment : Fragment() {
    var mDataset: MutableList<Backlink> = ArrayList()
    var viewManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)

    protected lateinit var mRecyclerView: RecyclerView
    public lateinit var mAdapter: BacklinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataset()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater?.inflate(R.layout.recycler_view_frag, container, false)
        mAdapter = BacklinkAdapter(mDataset)

        mRecyclerView = rootView!!.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = viewManager
            scrollToPosition(0)
        }

        return rootView
    }

    private fun initDataset() {
        mDataset.add(Backlink(0, 0, "Hello"))
    }
}