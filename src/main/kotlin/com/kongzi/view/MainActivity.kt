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
import com.kongzi.viewmodel.BacklinkViewModel
import com.kongzi.model.Backlink
import com.kongzi.model.BacklinkModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

var disposable: Disposable? = null

class MainActivity : AppCompatActivity() {

    lateinit var editTextPref: EditText
    lateinit var fragment: RecyclerViewFragment
    val adapter: BacklinkAdapter by lazy { fragment.mAdapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mViewModel = BacklinkViewModel(BacklinkModel())
        if (savedInstanceState == null) { setupViews() }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)
        val articlePref = sharedPref.getString(this.applicationContext.resources.getString
            (R.string.article_key), "Barack Obama")

        var editTextPref = findViewById<TextView>(R.id.articlefocus)
        editTextPref.setText(articlePref.toString())

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

    private var mCompositeSubscription: CompositeDisposable? = null
    private var mViewModel: BacklinkViewModel? = null

    fun bind() {
        mCompositeSubscription = CompositeDisposable()

        val mSubscription = mViewModel?.getBacklinks()
                ?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe (
                        { result -> setGreeting(result) },
                        { error -> Log.d("Henlo", error.toString()) } )

        if (mSubscription != null) mCompositeSubscription?.add(mSubscription)
    }

    private fun setGreeting(greeting: Backlink) {
        Log.d("Hello", "I changed " + greeting.title + " to 'It has changed!'")
        greeting.title = "It has changed!"
    }

    fun unbind() {
        mCompositeSubscription?.clear()
    }

    private fun setupViews() {
        val transaction = supportFragmentManager.beginTransaction()
        fragment = RecyclerViewFragment()
        transaction.replace(R.id.backlink_content_fragment, fragment)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        unbind()
        super.onPause()
    }

    /*
    fun getProductData() {
        disposable = wikiApiServe.backlinks(bltitle = "Donald Trump")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            result.query.backlinks.forEach {
                                //mDataset.add(it)
                            }
                        },
                        { error -> Log.d("RecyclerViewFragment", error.toString()) }
                )
    }*/
}
