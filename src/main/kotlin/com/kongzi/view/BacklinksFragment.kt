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
import android.util.Log
import android.widget.AdapterView
import android.widget.Spinner
import com.kongzi.model.Article
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

public class BacklinksFragment : Fragment() {

    var mDataset: MutableList<Backlink> = ArrayList()
    var mCallback: IFragmentToActivity? = null

    private var mArticlesSpinner: Spinner? = null
    private var mArticleSpinnerAdapter: ArticleSpinnerAdapter? = null

    //Should bundle this up in onSaveInstanceState so we don't have to query it many times
    private var mArticles: MutableList<Article> = emptyList<Article>().toMutableList()

    protected lateinit var mRecyclerView: RecyclerView
    public lateinit var mRVAdapter: BacklinksAdapter

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
        if (mArticleSpinnerAdapter != null) {
            mArticles.clear()
            mArticles.addAll(articles)
            mArticleSpinnerAdapter?.notifyDataSetChanged()
        } else {
            Log.d("BacklinksFragment", "mArticleSpinnerAdapter is null.")
        }
    }

    public fun updateBacklinkDisplay(backlinks: List<Backlink>) {
        mRVAdapter.refresh(backlinks)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.backlinks_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mRVAdapter = BacklinksAdapter(mDataset)
        mRecyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = mRVAdapter
            layoutManager = LinearLayoutManager(activity)
            scrollToPosition(0)
        }

        mArticlesSpinner = view?.findViewById(R.id.articles) as Spinner
        if (mArticlesSpinner == null) Log.d("BacklinksAdapter", "Could not create mArticlesSpinner")
        else {
            mArticleSpinnerAdapter = ArticleSpinnerAdapter(this.activity, R.layout.article_item, mArticles)
            if (mArticleSpinnerAdapter == null) Log.d("BacklinksAdapter", "Could not create mArticleSpinnerAdapter")
            else {
                Log.v("BacklinksFragment", "Just created mArticeSpinnerAdapter, so it should not be null from now on...")
                mArticlesSpinner?.adapter = mArticleSpinnerAdapter
            }

            mArticlesSpinner?.onItemSelectedListener = ItamSelectedListener
        }
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