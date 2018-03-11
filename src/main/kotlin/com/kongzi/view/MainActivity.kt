package com.kongzi.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import com.kongzi.*
import io.reactivex.disposables.Disposable
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.widget.Spinner
import com.kongzi.viewmodel.MainViewModel
import android.widget.AdapterView
import android.view.View
import com.kongzi.model.*
import org.intellij.lang.annotations.Language
import android.databinding.adapters.TextViewBindingAdapter.setText



class MainActivity : AppCompatActivity() {

    private lateinit var mCompositeDisposable: CompositeDisposable
    private var mDataModel: IDataModel = DataModel()
    private var mViewModel: MainViewModel = MainViewModel(getDataModel(), SchedulerProvider)

    private var mArticlesSpinner: Spinner? = null
    private var mArticleSpinnerAdapter:ArticleSpinnerAdapter? = null

    lateinit var fragment: RecyclerViewFragment
    val adapter: BacklinkAdapter by lazy { fragment.mAdapter }

    fun getDataModel(): IDataModel {
        return mDataModel
    }

    fun getViewModel(): MainViewModel {
        return MainViewModel(getDataModel(), SchedulerProvider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            setupViews()
        }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)
        val articlePref = sharedPref.getString(this.applicationContext.resources.getString
            (R.string.article_key), "Barack Obama")
        var focusTextPref = findViewById<TextView>(R.id.articlefocus)
        focusTextPref.setText(articlePref.toString())

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

        mCompositeDisposable.add(mViewModel.getCuoArticles()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setArticles))
    }

    private fun setBacklinks(backlink: Backlink) {
        Log.i("MainActivity", "backlink #${backlink.pageid}: ${backlink.title}")
    }

    private fun setArticles(articles: List<Article>) {
        mArticleSpinnerAdapter = ArticleSpinnerAdapter(this, R.layout.article_item, articles)
        mArticlesSpinner?.setAdapter(mArticleSpinnerAdapter)
    }

    fun unbind() {
        mCompositeDisposable.clear()
    }

    private fun setupViews() {
        val transaction = supportFragmentManager.beginTransaction()
        fragment = RecyclerViewFragment()
        transaction.replace(R.id.backlink_content_fragment, fragment)
        transaction.commit()

        mArticlesSpinner = findViewById(R.id.articles) as Spinner
        mArticlesSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                itemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //nothing to do here
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
