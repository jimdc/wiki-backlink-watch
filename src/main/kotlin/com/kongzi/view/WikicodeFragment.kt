package com.kongzi.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kongzi.R

class WikicodeFragment : Fragment() {

    private var callback: FragmentToActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = this.activity as FragmentToActivity
        } catch (cce: ClassCastException) {
            throw ClassCastException(activity.toString() +
                    " must implement FragmentToActivity")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (savedInstanceState == null) {
            val rootView = inflater?.inflate(R.layout.wikicode_fragment, container, false)

            val artDisplay = rootView?.findViewById<TextView>(R.id.articleEditing)
            artDisplay?.text = callback?.whichArticleInFocus()?.title

            return rootView
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

}