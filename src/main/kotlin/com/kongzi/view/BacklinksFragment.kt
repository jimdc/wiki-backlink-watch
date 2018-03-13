package com.kongzi.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import com.kongzi.R
import android.content.Context
import android.widget.AdapterView
import android.widget.Spinner
import com.kongzi.model.Article
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

public class BacklinksFragment : Fragment() {

    var mDataset: MutableList<Backlink> = ArrayList()
    var mCallback: IFragmentToActivity? = null

    private var mArticlesSpinner: Spinner? = null
    private var mArticleSpinnerAdapter: ArticleSpinnerAdapter? = null

    protected lateinit var mRecyclerView: RecyclerView
    public lateinit var mRVAdapter: BacklinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataset()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallback = this.activity as IFragmentToActivity
        } catch (cce: ClassCastException) {
            throw ClassCastException(activity.toString() +
                    " must implement IFragmentToActivity")
        }
    }

    override fun onDetach() {
        mCallback = null
        super.onDetach()
    }

    public fun updateArticlesDisplay(articles: List<Article>) {
        mArticleSpinnerAdapter = ArticleSpinnerAdapter(this.context, R.layout.article_item, articles)
        mArticlesSpinner?.setAdapter(mArticleSpinnerAdapter)
    }

    public fun updateBacklinkDisplay(backlinks: List<Backlink>) {
        mRVAdapter.refresh(backlinks)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (savedInstanceState == null) {
            var rootView = inflater?.inflate(R.layout.recycler_view_frag, container, false)

            mArticlesSpinner = rootView?.findViewById(R.id.articles) as Spinner
            mArticlesSpinner?.onItemSelectedListener = ItamSelectedListener

            mRVAdapter = BacklinkAdapter(mDataset)
            mRecyclerView = rootView!!.findViewById<RecyclerView>(R.id.recyclerView).apply {
                setHasFixedSize(true)
                adapter = mRVAdapter
                layoutManager = LinearLayoutManager(activity)
                scrollToPosition(0)
            }

            return rootView
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    val ItamSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
            //Nothing to do here, but required to implement
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val articleSelected = mArticleSpinnerAdapter?.getItem(position)
            if (articleSelected != null) {
                mCallback?.selectArticle(articleSelected)
            }
        }
    }

    private fun initDataset() {
        mDataset.add(Backlink(0, 0, "Hello"))
    }
}