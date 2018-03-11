package com.kongzi.view

import android.support.v7.widget.RecyclerView
import com.kongzi.model.Backlink
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import com.kongzi.R

public class BacklinkAdapter(var blinks: List<Backlink>) : RecyclerView.Adapter<BacklinkAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BacklinkAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.brow_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        blinks[position].let {
            holder.row1?.text = it.title
            holder.row2?.text = it.pageid.toString()
        }
    }

    override fun getItemCount(): Int {
        return blinks.size
    }
}