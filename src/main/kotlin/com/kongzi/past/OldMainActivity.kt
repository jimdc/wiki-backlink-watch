package com.kongzi.past

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import io.reactivex.disposables.CompositeDisposable
import android.view.View
import android.view.MenuItem
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import com.kongzi.R
import com.kongzi.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class OldMainActivity : FragmentToActivity, DaggerAppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private var maineViewModel: MaineViewModel = MaineViewModel(DataModel())
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            compositeDisposable = CompositeDisposable()
        }

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
                val myIntent = Intent(this@OldMainActivity, SettingsActivity::class.java)
                startActivity(myIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun selectArticle (article: Article) {
        maineViewModel.articleSelected(article)
    }

    override fun whichArticleInFocus(): Article {
        return with (maineViewModel) {
            if (selectedArticle.hasValue()) selectedArticle.value  else Article(-1, "maineViewModel uninitialized")
        }
    }
}
