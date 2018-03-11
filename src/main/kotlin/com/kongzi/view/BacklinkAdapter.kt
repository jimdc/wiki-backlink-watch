package com.kongzi.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import com.kongzi.model.Backlink
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import com.kongzi.R
import com.kongzi.viewmodel.BacklinkViewModel

public class BacklinkAdapter : RecyclerView.Adapter<BacklinkAdapter.ViewHolder> {
    lateinit var blinks: List<Backlink>
    constructor (backlinks: List<Backlink>) { blinks = backlinks }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.brow_list, parent, false))
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

/*
class BacklinkAdapter (private var backlinks: List<Backlink>)
    : RecyclerView.Adapter<BacklinkAdapter.BacklinkViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BacklinkViewHolder {
        var backlinkBinding: BrowListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.brow_list, parent, false)
        return BacklinkViewHolder(backlinkBinding)
    }

    override fun onBindViewHolder(holder: BacklinkViewHolder, position: Int) {
        holder.bindBacklink(backlinks.get(position))
    }

    fun setBacklinks(newblinks: List<Backlink>) {
        this.backlinks = newblinks
        notifyDataSetChanged()
    }

    override fun getItemCount() = backlinks.size

    class BacklinkViewHolder(internal var mBacklinkBinding: BrowListBinding) :
            RecyclerView.ViewHolder(mBacklinkBinding.pageid) {

        internal fun bindBacklink(blinkStream: Backlink) {
            if (mBacklinkBinding.getBacklinkViewModel() == null) {
                mBacklinkBinding.setBacklinkViewModel(BacklinkViewModel(blinkStream, itemView.context))
            } else {
                mBacklinkBinding.getBacklinkViewModel().setBacklink(blinkStream)
            }
        }
    }
}
        */