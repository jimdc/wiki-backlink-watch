package com.kongzi.view

import android.support.v7.widget.RecyclerView
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kongzi.R

public class BacklinkAdapter : RecyclerView.Adapter<BacklinkAdapter.ViewHolder> {
    lateinit var blinks: List<Backlink>
    constructor (backlinks: List<Backlink>) { blinks = backlinks }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.backlink_list_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        blinks[position].let {
            holder?.row1?.text = it.title
            holder?.row2?.text = it.pageid.toString()
        }
    }

    override fun getItemCount(): Int {
        return blinks.size
    }
}