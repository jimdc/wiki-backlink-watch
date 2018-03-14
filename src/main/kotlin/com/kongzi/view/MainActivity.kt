package com.kongzi.view

import android.content.Intent
import android.os.Bundle
import android.provider.Contacts
import com.kongzi.*
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.kongzi.viewmodel.MainViewModel
import android.view.View
import com.kongzi.model.*
import android.view.MenuItem
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import android.util.Log
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainActivity : IFragmentToActivity, AppCompatActivity() {

    private lateinit var mCompositeDisposable: CompositeDisposable
    private var mViewModel: MainViewModel = MainViewModel(DataModel())
    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /*
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val articlePref = sharedPref.getString(resources.getString(R.string.articlelist_key), "Banana Fish")
        setSpinText(articlePref)
        */

        val toolbar = findViewById(R.id.cooltoolbar) as? android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        val mViewPager = findViewById<View>(R.id.viewpager) as ViewPager
        mAdapter = ViewPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(mViewPager)

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

    private fun setBacklinks(backlinks: List<Backlink>) {

        val backfrag = mAdapter.getItem(0)
        try {
            val inteopeiseu = backfrag as? BacklinksFragment
            if (inteopeiseu != null) {
                inteopeiseu.updateBacklinkDisplay(backlinks)
            } else {
                Log.d("MainActivity", "findFragmentByTag returned null")
            }
        } catch (cce: ClassCastException) {
            Log.d("MainActivity", "could not cast caught fragment to BacklinksFragment")
        }
    }

    private fun setArticles(articles: List<Article>) {

        val backfrag = mAdapter.getItem(0)
        try {
            val inteopeiseu = backfrag as? BacklinksFragment
            if (inteopeiseu != null) {
                launch(UI) {
                    delay(500) // wait half a second
                    inteopeiseu.updateArticlesDisplay(articles)
                }
            } else {
                Log.d("MainActivity", "findFragmentByTag returned null")
            }
        } catch (cce: ClassCastException) {
            Log.d("MainActivity", "could not cast caught fragment to BacklinksFragment")
        }
    }

    override fun selectArticle (article: Article) {
        mViewModel.articleSelected(article)
    }

    override fun whichArticleInFocus(): Article {
        if (mViewModel.mSelectedArticle.hasValue())
            return mViewModel.mSelectedArticle.value
        else
            return Article(-1, "mViewModel has no value yet")
    }

    override fun onResume() {
        super.onResume()
        bind()
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

    override fun onPause() {
        super.onPause()
        unbind()
    }

    fun unbind() {
        mCompositeDisposable.clear()
    }
}
