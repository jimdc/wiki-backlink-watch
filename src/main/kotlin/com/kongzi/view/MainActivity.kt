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
import android.util.Log

class MainActivity : FragmentToActivity, AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private var mainViewModel: MainViewModel = MainViewModel(DataModel())
    private lateinit var viewPagerAdapter: ViewPagerAdapter

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

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
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

        val backlinksFragment = viewPagerAdapter.getItem(0)
        try {
            val interfaceToBacklinks = backlinksFragment as? BacklinksFragment
            if (interfaceToBacklinks != null) {
                interfaceToBacklinks.updateBacklinkDisplay(backlinks)
            } else {
                Log.d("MainActivity", "findFragmentByTag returned null")
            }
        } catch (cce: ClassCastException) {
            Log.d("MainActivity", "could not cast caught fragment to BacklinksFragment")
        }
    }

    private fun setArticles(articles: List<Article>) {

        val backlinksFragment = viewPagerAdapter.getItem(0)
        try {
            val interfaceToBacklinks = backlinksFragment as? BacklinksFragment
            if (interfaceToBacklinks != null) {
                interfaceToBacklinks.updateArticlesDisplay(articles)
            } else {
                Log.d("MainActivity", "findFragmentByTag returned null")
            }
        } catch (cce: ClassCastException) {
            Log.d("MainActivity", "could not cast caught fragment to BacklinksFragment")
        }
    }

    override fun selectArticle (article: Article) {
        mainViewModel.articleSelected(article)
    }

    override fun whichArticleInFocus(): Article {
        return with (mainViewModel) {
            if (selectedArticle.hasValue()) selectedArticle.value  else Article(-1, "mainViewModel uninitialized")
        }
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    fun bind() {
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(mainViewModel.getBacklinks()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setBacklinks))

        compositeDisposable.add(mainViewModel.getCuoArticles(this.applicationContext)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setArticles))
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }

    fun unbind() {
        compositeDisposable.clear()
    }
}
