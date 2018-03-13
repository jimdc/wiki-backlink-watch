package com.kongzi.view

import android.content.Intent
import android.os.Bundle
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

class MainActivity : IFragmentToActivity, AppCompatActivity() {

    private lateinit var mCompositeDisposable: CompositeDisposable
    private var mViewModel: MainViewModel = MainViewModel(DataModel())

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

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

        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

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
        val backlinksFrag = fragmentManager.findFragmentById(R.id.backlink_replaced_fragment) as? BacklinksFragment
        if (backlinksFrag != null) {
            backlinksFrag.updateBacklinkDisplay(backlinks)
        }
    }

    private fun setArticles(articles: List<Article>) {
        val backlinksFrag = fragmentManager.findFragmentById(R.id.backlink_replaced_fragment) as? BacklinksFragment
        if (backlinksFrag != null) {
            backlinksFrag.updateArticlesDisplay(articles)
        }
    }

    override fun selectArticle (article: Article) {
        mViewModel.articleSelected(article)
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
