package com.kongzi.view

import android.content.Intent
import android.os.Bundle
import com.kongzi.*
import io.reactivex.disposables.Disposable
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.kongzi.viewmodel.MainViewModel
import android.view.View
import com.kongzi.model.*
import org.intellij.lang.annotations.Language
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.view.MenuItem
import android.widget.*
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

class MainActivity : AppCompatActivity() {

    private lateinit var mCompositeDisposable: CompositeDisposable
    private var mViewModel: MainViewModel = MainViewModel(DataModel(), SchedulerProvider)

    private var mArticlesSpinner: Spinner? = null
    private var mArticleSpinnerAdapter:ArticleSpinnerAdapter? = null

    lateinit var fragment: RecyclerViewFragment
    val adapter: BacklinkAdapter by lazy { fragment.mAdapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            setupViews()
        }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val articlePref = sharedPref.getString(resources.getString(R.string.articlelist_key), "Banana Fish")
        setSpinText(articlePref)

        val toolbar = findViewById(R.id.cooltoolbar) as? android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val myIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(myIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun bind() {
        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable.add(mViewModel.getBacklinks()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setBacklinks))

        mCompositeDisposable.add(mViewModel.getCuoArticles(this.applicationContext)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setArticles))
    }

    private fun setBacklinks(backlinks: List<Backlink>) {
        if (::fragment.isInitialized)
            adapter.refresh(backlinks)
    }

    private fun setArticles(articles: List<Article>) {
        mArticleSpinnerAdapter = ArticleSpinnerAdapter(this, R.layout.article_item, articles)
        mArticlesSpinner?.setAdapter(mArticleSpinnerAdapter)
    }

    /**
     * @todo: Eliminate the race condition here that comes from constantly re-creating the adapter ^^
     */
    fun setSpinText(text: String) {
        if (mArticlesSpinner != null && mArticleSpinnerAdapter != null) {
            val count = mArticleSpinnerAdapter?.count
            for (i in 0 until (count ?: 0)) {
                if (mArticleSpinnerAdapter?.getItem(i).toString().contains(text)) {
                    mArticlesSpinner?.setSelection(i)
                }
            }
        }
    }

    fun unbind() {
        mCompositeDisposable.clear()
    }

    /**
     * Setup [mArticlesSpinner] and [RecyclerViewFragment]
     */
    private fun setupViews() {
        fragment = RecyclerViewFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.backlink_content_fragment, fragment).commit()

        mArticlesSpinner = findViewById(R.id.articles) as Spinner
        mArticlesSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Nothing to do here, but required to implement
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                itemSelected(position)
            }
        }
    }

    private fun itemSelected(position: Int) {
        val articleSelected = mArticleSpinnerAdapter?.getItem(position)
        if (articleSelected != null)
            mViewModel.articleSelected(articleSelected)
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }
}
