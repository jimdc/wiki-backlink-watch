package com.kongzi.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import com.kongzi.R

public class WikicodeFragment : Fragment() {

    var mCallback: IFragmentToActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallback = this.activity as IFragmentToActivity
        } catch (cce: ClassCastException) {
            throw ClassCastException(activity.toString() +
                    " must implement IFragmentToActivity")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (savedInstanceState == null) {
            var rootView = inflater?.inflate(R.layout.wikicode_fragment, container, false)

            var artDisplay = rootView?.findViewById<TextView>(R.id.articleEditing)
            artDisplay?.text = mCallback?.whichArticleInFocus()?.title

            return rootView
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDetach() {
        mCallback = null
        super.onDetach()
    }

}