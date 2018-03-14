package com.kongzi.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kongzi.R

class DefaultTabFragment : Fragment() {

    internal var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments.getInt("pos")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.default_tab_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = "Fragment ${position+1}"
    }

    companion object {

        fun getInstance(position: Int): Fragment {
            when(position) {
                0 -> { return BacklinksFragment() }
                1 -> { return WikicodeFragment() }
                // 2 -> use CheckedTextView for diffs
                else -> {
                    val bundle = Bundle()
                    bundle.putInt("pos", position)

                    val tabFragment = DefaultTabFragment()
                    tabFragment.arguments = bundle
                    return tabFragment
                }
            }
        }
    }
}